package com.data.extractor.service;

import com.data.extractor.dto.IpAddressDetails;
import com.data.extractor.exception.AppErrorCode;
import com.data.extractor.exception.UnAuthorizedRequesterException;
import com.data.extractor.util.DataFileFormat;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class DefaultOutputGenerator implements OutputFileGenerator{

    private final IpProcessor ipProcessor;
    private final RequestDetailsRecorder requestDetailsRecorder;

    public DefaultOutputGenerator(IpProcessor ipProcessor, RequestDetailsRecorder requestDetailsRecorder) {
        this.ipProcessor = ipProcessor;
        this.requestDetailsRecorder = requestDetailsRecorder;
    }

    public File generate(MultipartFile file, HttpServletRequest request) throws IOException {
        IpAddressDetails addressDetails = ipProcessor.validateIp(request.getRemoteAddr());
        requestDetailsRecorder.recordDetails(request, addressDetails);
        if ("success".equals(addressDetails.status())) {
            return DataFileFormat.convertFromTxtToJSON(file);
        }
        throw new UnAuthorizedRequesterException(AppErrorCode.INVALID_REQUESTER, addressDetails.message());
    }
}
