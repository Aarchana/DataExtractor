package com.data.extractor.integration;


import com.data.extractor.ExtractorApplication;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@TestPropertySource(properties = {"feature-flag.enable-requester-validation=false"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ExtractorApplication.class)
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8181)
public class DefaultDataExtractorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${feature-flag.enable-requester-validation}")
    private boolean featureFlagToValidateFile;

    @Test
    public void OutputsEmptyJsonFileWhenGivenEmptyTxtFile() throws Exception {
        assertThat(featureFlagToValidateFile).isFalse();
        stubFor(get(urlPathMatching("/json/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "query": "24.48.0.1",
                                  "status": "success",
                                  "country": "Canada",
                                  "countryCode": "CA",
                                  "isp": "Le Groupe Videotron Ltee"
                                }""")));
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "emptyFile.txt",
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(Paths.get("src/test/resources/emptyFile.txt"))
        );
        mockMvc.perform(multipart("/parse").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    assertLinesMatch(Files.readAllLines(Paths.get("outcome.json")),
                            Files.readAllLines(Paths.get("src/test/resources/noDataOutcomeFile.json")));
                });
    }

}
