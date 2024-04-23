package com.data.extractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractorApplication.class, args);
	}

}
