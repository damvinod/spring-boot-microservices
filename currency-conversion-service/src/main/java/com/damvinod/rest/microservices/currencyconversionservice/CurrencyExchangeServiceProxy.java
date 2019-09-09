package com.damvinod.rest.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8050") //This can be used with out Ribbon which is client side load balancer
@FeignClient(name="currency-exchange-service") //This is commented to make sure that the currency-exchange-service is invoked through zuul server
//@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}") // This is added to invoke currency-exchange-service through zuul server
	public CurrencyConversionBean retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}

//syntax for invoking microservices through zuul server is http://localhost:8765/{spring.application.name}/{uri}

//These are the url's to invoke through zuul server

//http://localhost:8765/currency-conversion-service/currency-convertor-feign/from/EUR/to/INR/quantity/10000
//http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR

//http://localhost:8100/currency-convertor-feign/from/EUR/to/INR/quantity/10000
//http://localhost:8050/currency-exchange/from/USD/to/INR