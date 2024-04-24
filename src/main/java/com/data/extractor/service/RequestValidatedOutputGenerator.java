package com.data.extractor.service;

import com.data.extractor.exception.InvalidFileFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.data.extractor.exception.AppErrorCode.INVALID_FILE_CONTENT;
import static com.data.extractor.exception.AppErrorCode.INVALID_FILE_TYPE;
import static com.data.extractor.util.FileValidator.isFileEmpty;
import static com.data.extractor.util.FileValidator.isFileOfTypeText;

public class RequestValidatedOutputGenerator extends DefaultOutputGenerator {

    public RequestValidatedOutputGenerator(IpProcessor ipProcessor, RequestDetailsRecorder requestDetailsRecorder) {
        super(ipProcessor, requestDetailsRecorder);
    }

    @Override
    public File generate(MultipartFile file, HttpServletRequest request) throws IOException, InvalidFileFormatException {
            if (isFileEmpty(file)) {
                throw new InvalidFileFormatException(INVALID_FILE_CONTENT, INVALID_FILE_CONTENT.getMessage());
            }

            if(!isFileOfTypeText(file)) {
                throw new InvalidFileFormatException(INVALID_FILE_TYPE, INVALID_FILE_TYPE.getMessage());
            }

            return super.generate(file, request);
    }
}
