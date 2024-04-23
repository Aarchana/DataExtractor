package com.data.extractor.util;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

    public static boolean isFileOfTypeText(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().equals(MediaType.TEXT_PLAIN_VALUE);
    }

    public static boolean isFileEmpty(MultipartFile file) {
        return file.isEmpty();
    }
}
