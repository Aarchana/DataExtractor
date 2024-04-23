package com.data.extractor.configuration;

import com.data.extractor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ValidatorConfiguration {

    private final IpProcessor ipProcessor;
    private final RequestDetailsRecorder requestDetailsRecorder;

    @ConditionalOnProperty(
            name = "feature-flag.enable-requester-validation",
            havingValue = "false",
            matchIfMissing = true
    )
    @Bean
    public OutputFileGenerator defaultOutputFileGenerator() {
        return new DefaultOutputGenerator(ipProcessor, requestDetailsRecorder);
    }

    @ConditionalOnProperty(
            name = "feature-flag.enable-requester-validation",
            havingValue = "true"
    )
    @Bean
    public OutputFileGenerator requesterValidatedOutputFileGenerator() {
        return new RequestValidatedOutputGenerator(ipProcessor, requestDetailsRecorder);
    }
}
