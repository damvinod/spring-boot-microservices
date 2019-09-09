package com.damvinod.rest.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangevalueRepository extends JpaRepository<ExcahngeValue, Long>{
	
	public ExcahngeValue findByFromAndTo(String from, String to);

}
