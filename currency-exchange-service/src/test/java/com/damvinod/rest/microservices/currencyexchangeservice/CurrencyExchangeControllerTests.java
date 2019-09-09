package com.damvinod.rest.microservices.currencyexchangeservice;

import java.math.BigDecimal;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ExchangevalueRepository exchangevalueRepository;
	
	ExcahngeValue exchangeValue;
	
	Supplier<ExcahngeValue> supplier = () -> {
		exchangeValue = new ExcahngeValue();
		exchangeValue.setId(1L);
		exchangeValue.setFrom("USD");
		exchangeValue.setTo("INR");
		exchangeValue.setConversionMultiple(new BigDecimal("65"));
		exchangeValue.setPort(8050);
		return exchangeValue;
	};
	
	@Before
	public void setUp() {
		supplier.get();
	}
	
	@Test
	public void retriveExchangeValue() throws Exception {
		given(this.exchangevalueRepository.findByFromAndTo("USD", "INR")).willReturn(exchangeValue);
		mockMvc.perform(get("/currency-exchange/from/{from}/to/{to}", "USD", "INR").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.from", is("USD")))
		.andExpect(jsonPath("$.to", is("INR")))
		.andExpect(jsonPath("$.conversionMultiple", is(65)))
		.andExpect(jsonPath("$.port", is(8050)));
	}
}
