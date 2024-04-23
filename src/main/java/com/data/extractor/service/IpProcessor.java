package com.data.extractor.service;

import com.data.extractor.client.IpValidatorClient;
import com.data.extractor.dto.IpAddressDetails;
import com.data.extractor.exception.AppErrorCode;
import com.data.extractor.exception.UnAuthorizedRequesterException;
import com.data.extractor.repository.FileRequestMetaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class IpProcessor {


    public static final String IP_SERVICE_FIELD = "countryCode,status,isp,query,country";
    private final IpValidatorClient ipValidatorClient;
    private static final List<String> BLOCKED_COUNTRIES = List.of("USA", "Spain", "China");
    private static final List<String> BLOCKED_ISP = List.of("Amazon.com, Inc.", "GCP", "Azure");
    private final FileRequestMetaDataRepository fileRequestMetaDataRepository;

    IpAddressDetails validateIp(String requesterIp) {
        IpAddressDetails addressDetails = getIpAddressDetails(requesterIp);
        if (isValidRequester(addressDetails)) {
            throw new UnAuthorizedRequesterException(AppErrorCode.INVALID_REQUESTER, AppErrorCode.INVALID_REQUESTER.getMessage());
        }
        return addressDetails;
    }

    private static boolean isValidRequester(IpAddressDetails addressDetails) {
        return nonNull(addressDetails.country()) && nonNull(addressDetails.isp()) && (BLOCKED_COUNTRIES.contains(addressDetails.country()) || BLOCKED_ISP.contains(addressDetails.isp()));
    }

    private IpAddressDetails getIpAddressDetails(String requesterIp) {
        IpAddressDetails addressDetails;
        try {
            addressDetails = ipValidatorClient.validateIp(requesterIp, IP_SERVICE_FIELD);
            if (!"success".equals(addressDetails.status())) {
                throw new UnAuthorizedRequesterException(AppErrorCode.INVALID_REQUESTER, addressDetails.message());
            }
        } catch (Exception ex) {
            throw new UnAuthorizedRequesterException(AppErrorCode.INVALID_REQUESTER, ex.getLocalizedMessage());
        }
        return addressDetails;
    }
}