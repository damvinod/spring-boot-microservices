package com.damvinod.rest.microservices.limitservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damvinod.rest.microservices.limitservices.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration configuration; 
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFormConfigurations(){
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
}
