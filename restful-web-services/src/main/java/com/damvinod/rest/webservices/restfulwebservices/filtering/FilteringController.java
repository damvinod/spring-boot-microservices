package com.damvinod.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public StaticBeanFilter retriveStaticBeanFilter(){
		return new StaticBeanFilter("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<StaticBeanFilter> retriveStaticBeanFilterList(){
		return Arrays.asList(new StaticBeanFilter("value1", "value2", "value3"),new StaticBeanFilter("value11", "value22", "value33"));
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retriveDynamicBeanFilter(){
		
		DynamicBeanFilter dynamicBeanFilter = new DynamicBeanFilter("value1", "value2", "value3");
		
		return createMappingJacksonValue(dynamicBeanFilter);
	}
	
	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue retriveDynamicBeanFilterList(){
		
		List<DynamicBeanFilter> dynamicBeanFilter = Arrays.asList(new DynamicBeanFilter("value1", "value2", "value3"),new DynamicBeanFilter("value11", "value22", "value33"));;
		
		return createMappingJacksonValue(dynamicBeanFilter);
	}
	
	private MappingJacksonValue createMappingJacksonValue(Object inputObject){
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");//property names in pojo
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicBeanFilterName", simpleBeanPropertyFilter);//This name given in @JsonFiler in the Pojo
		
		MappingJacksonValue mapping = new MappingJacksonValue(inputObject);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
}
