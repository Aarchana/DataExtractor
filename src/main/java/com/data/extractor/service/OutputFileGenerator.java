package com.data.extractor.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface OutputFileGenerator {
    File generate(MultipartFile file, HttpServletRequest httpServletRequest) throws IOException;
}
