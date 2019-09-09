package com.damvinod.rest.microservices.currencyexchangeservice;


import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment environment;
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	ExchangevalueRepository exchangevalueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExcahngeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to){
		//ExcahngeValue excahngeValue = new ExcahngeValue(1000L, from, to, BigDecimal.valueOf(65));
		
		ExcahngeValue exchangeValue = exchangevalueRepository.findByFromAndTo(from, to);
		
		if(environment.getProperty("local.server.port") != null)
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		log.info("excahngeValue.. {}", exchangeValue);
		
		log.info("services...." + discoveryClient.getServices());
		
		Consumer<String> myConsumer = (String string) -> {
			discoveryClient.getInstances(string).forEach(
					serviceInstance -> System.out.println(serviceInstance.getPort() + " " + serviceInstance.getHost()
							+ " " + serviceInstance.getServiceId() + " " + serviceInstance.getUri().toString()));
		};
		
		discoveryClient.getServices().forEach(myConsumer);
		
		return exchangeValue;
	}
}
