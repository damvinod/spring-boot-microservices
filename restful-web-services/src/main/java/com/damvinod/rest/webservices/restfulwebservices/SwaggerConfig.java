package com.damvinod.rest.webservices.restfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Vinod Dampuru", "damvinod.com", "vinodreddi@ymail.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My sample Api Documentation", "My sample Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	private static final Set<String> DEFAULT_CONSUMES_PRODUCES = new HashSet<String>(Arrays.asList("application/json","application/xml"));
	  
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.consumes(DEFAULT_CONSUMES_PRODUCES)
				.produces(DEFAULT_CONSUMES_PRODUCES);
	}
}
