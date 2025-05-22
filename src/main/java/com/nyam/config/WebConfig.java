package com.nyam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String address = "http://localhost:5173";
		
		registry.addMapping("/**")
		.allowedOrigins(address)
		.allowedMethods("*")
		.allowCredentials(true);
	}
	
	
}
