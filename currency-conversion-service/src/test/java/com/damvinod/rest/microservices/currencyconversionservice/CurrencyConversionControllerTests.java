package com.damvinod.rest.microservices.currencyconversionservice;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyConversionController.class)
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
public class CurrencyConversionControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	CurrencyConversionBean currencyConversionBean;
	
	Supplier<CurrencyConversionBean> supplier = () -> {
		currencyConversionBean = new CurrencyConversionBean();
		currencyConversionBean.setId(1L);
		currencyConversionBean.setFrom("USD");
		currencyConversionBean.setTo("INR");
		currencyConversionBean.setConversionMultiple(new BigDecimal("65"));
		currencyConversionBean.setPort(8050);
		currencyConversionBean.setQuantity(new BigDecimal("100"));
		return currencyConversionBean;
	};
	
	@Before
	public void setUp() {
		supplier.get();
	}
	
	@Test
	public void covertCurrency() throws Exception {
		given(this.currencyExchangeServiceProxy.retriveExchangeValue("USD", "INR")).willReturn(currencyConversionBean);
		mockMvc.perform(get("/currency-convertor-feign/from/{from}/to/{to}/quantity/{quantity}", "USD", "INR", "100").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalCalculatedAmount", is(6500)));
	}
}
