package com.data.extractor.util;

import com.data.extractor.exception.AppErrorCode;
import com.data.extractor.exception.UnAuthorizedRequesterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class DataFileFormat {

    public static File convertFromTxtToJSON(MultipartFile file) throws IOException {
        File jsonFile;
        try {
            jsonFile = JsonFileBuilder.buildJsonFile(TextFileParser.parse(file.getBytes()));
        } catch (Exception ex) {
            throw new UnAuthorizedRequesterException(AppErrorCode.INVALID_REQUESTER, AppErrorCode.INVALID_REQUESTER.getMessage());
        }
       return jsonFile;
    }




}
