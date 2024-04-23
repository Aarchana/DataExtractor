package com.data.extractor.client;

import com.data.extractor.dto.IpAddressDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "ip-validator", url = "${client.ip_validator.host}")
public interface IpValidatorClient {
    @RequestMapping(method = RequestMethod.GET, value = "/json/{query}")
    IpAddressDetails validateIp(@PathVariable String query, @RequestParam String fields);
}
