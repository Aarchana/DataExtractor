package com.data.extractor.service;

import com.data.extractor.client.IpValidatorClient;
import com.data.extractor.dto.IpAddressDetails;
import com.data.extractor.exception.UnAuthorizedRequesterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.data.extractor.service.IpProcessor.IP_SERVICE_FIELD;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpProcessorTest {

    @Mock
    private IpValidatorClient ipValidatorClient;

    @InjectMocks
    private IpProcessor ipProcessor;


    @Test
    public void shouldThrowExceptionWhenResponseHasStatusOtherThanSuccess() {
        String requesterIp = "127.0.0.1";
        when(ipValidatorClient.validateIp(requesterIp, IP_SERVICE_FIELD)).thenReturn(reservedRangeResponse());
        assertThrows(UnAuthorizedRequesterException.class, () -> ipProcessor.validateIp(requesterIp));
    }

    @Test
    public void shouldBlockAndThrowForbiddenForUSAChinaAndSpain() {
        String requesterIp = "223.255.252.0";
        when(ipValidatorClient.validateIp(requesterIp, IP_SERVICE_FIELD)).thenReturn(BlockedCountryResponse());
        assertThrows(UnAuthorizedRequesterException.class, () -> ipProcessor.validateIp(requesterIp));
    }

    @Test
    public void shouldBlockAndThrowForbiddenForBlockedIps() {
        String requesterIp = "223.255.252.0";
        when(ipValidatorClient.validateIp(requesterIp, IP_SERVICE_FIELD)).thenReturn(blockedISPResponse());
        assertThrows(UnAuthorizedRequesterException.class, () -> ipProcessor.validateIp(requesterIp));
    }

    public static IpAddressDetails reservedRangeResponse() {
        return new IpAddressDetails("fail", "reserved range", "127.0.0.1", null, null, null );
    }

    public static IpAddressDetails BlockedCountryResponse() {
        return new IpAddressDetails("success", null, "223.255.252.0", "CN", null, "China" );
    }

    public static IpAddressDetails blockedISPResponse() {
        return new IpAddressDetails("success", "reserved range", "52.204.122.132", "reserved range", "Amazon.com, Inc.", "United States" );
    }
}
