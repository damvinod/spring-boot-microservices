package com.damvinod.rest.microservices.swaggerservice.config;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
//@EnableAutoConfiguration
//@EnableConfigurationProperties
//@Configuration
//@ConfigurationProperties(prefix="documentation.swagger")
public class SwaggerServicesConfig {
	
	List<SwaggerService> services;

	public List<SwaggerService> getServices() {
		return services;
	}

	public void setServices(List<SwaggerService> services) {
		this.services = services;
	}

	@EnableConfigurationProperties
	//@Configuration
	@ConfigurationProperties(prefix="documentation.swagger.services")
	public static class SwaggerService {
		
		private String name;
		private String url;
		private String version;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
		@Override
		public String toString() {
			return "SwaggerService [name=" + name + ", url=" + url + ", version=" + version + "]";
		}
	}
}