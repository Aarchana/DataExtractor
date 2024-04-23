package com.data.extractor.service;

import com.data.extractor.dto.IpAddressDetails;
import com.data.extractor.repository.FileRequestMetaDataRepository;
import com.data.extractor.repository.dao.FileRequestMetadata;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RequestDetailsRecorder {

    private final FileRequestMetaDataRepository fileRequestMetaDataRepository;

    void recordDetails(HttpServletRequest httpServletRequest, @Nullable IpAddressDetails addressDetails) {
        IpAddressDetails details = Optional.ofNullable(addressDetails).orElse(IpAddressDetails.defaultValue());
        FileRequestMetadata requestMetadata = FileRequestMetadata.builder()
                .requestId(UUID.randomUUID().toString())
                .requestUri(httpServletRequest.getRequestURI())
                .countryCode(details.countryCode())
                .ipValidationResponseCode(details.status())
                .requestTimestamp((LocalDateTime) httpServletRequest.getAttribute("startTime"))
                .responseTimestamp(LocalDateTime.now())
                .ipAddress(httpServletRequest.getRemoteAddr())
                .isp(details.isp())
                .build();
        fileRequestMetaDataRepository.save(requestMetadata);
    }
}
