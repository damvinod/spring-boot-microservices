package com.damvinod.rest.microservices.oauth2authserver;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Oauth2AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthServerApplication.class, args);
	}
	
	@PostMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@PostMapping("/vinod")
	public String getVinodString(){
		return "ram";
	}
}
