package com.damvinod.rest.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients("com.damvinod.rest.microservices.currencyconversionservice")
@EnableDiscoveryClient
// This annotation is for Eureka Client
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
	
	// This is added for sleuth which will help to create unique id in the logs across the microservices which can be used for tracing purpose
	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
