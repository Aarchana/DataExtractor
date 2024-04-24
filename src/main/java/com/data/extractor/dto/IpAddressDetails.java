package com.data.extractor.dto;

public record IpAddressDetails(String status, String message, String query, String countryCode, String isp, String country) {
    public static IpAddressDetails defaultValue() {
        return new IpAddressDetails("", "", "", "", "", "");
    }
}
