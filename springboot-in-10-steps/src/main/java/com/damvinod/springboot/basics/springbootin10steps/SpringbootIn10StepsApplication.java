package com.damvinod.springboot.basics.springbootin10steps;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootIn10StepsApplication {

	public static void main(String[] args) {
		
		ApplicationContext appContext = SpringApplication.run(SpringbootIn10StepsApplication.class, args);
		
		List<String> beanDefinitionNamesList = Arrays.asList(appContext.getBeanDefinitionNames());

		beanDefinitionNamesList.forEach((Object beanName) -> System.out.println("bean name..." + beanName));
	}
}