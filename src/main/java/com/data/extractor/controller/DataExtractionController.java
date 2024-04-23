package com.data.extractor.controller;

import com.data.extractor.service.OutputFileGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class DataExtractionController {

    private final OutputFileGenerator outputFileGenerator;

    public DataExtractionController(OutputFileGenerator outputFileGenerator) {
        this.outputFileGenerator = outputFileGenerator;
    }

    @PostMapping("/parse")
    public File createJsonFromCsv(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        return this.outputFileGenerator.generate(file, request);
    }

}
