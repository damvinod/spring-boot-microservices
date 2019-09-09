package com.damvinod.rest.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping("/currency-convertor/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean covertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntiry = new RestTemplate().getForEntity(
				"http://localhost:8050/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
		
		CurrencyConversionBean responseBean = responseEntiry.getBody();
		
		System.out.println("responseBean..." + responseBean);
		
		return new CurrencyConversionBean(responseBean.getId(), from, to, responseBean.getConversionMultiple(),
				quantity, quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}
	
	@GetMapping("/currency-convertor-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean covertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
		
		CurrencyConversionBean responseBean = currencyExchangeServiceProxy.retriveExchangeValue(from, to);
		
		log.info("responseBean.. {}", responseBean);
		
		return new CurrencyConversionBean(responseBean.getId(), from, to, responseBean.getConversionMultiple(),
				quantity, quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}
}
