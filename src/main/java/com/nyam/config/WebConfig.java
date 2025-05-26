package com.nyam.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nyam.interceptor.LoginInterceptor;
import com.nyam.interceptor.RateLimitInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Autowired
	RateLimitInterceptor rateLimitInterceptor;

		@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path path = Paths.get("src/main/resources/static/image");
		registry.addResourceHandler("/image/**")
				.addResourceLocations("file:"+path.toAbsolutePath().toString())
				.setCachePeriod(1000);
	}
	
	// LoginInterceptor 추가
	// 모든 api를 막아 놓되, login, regist 페이지는 접속할 수 있게 한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/api/**")
		.excludePathPatterns("/api/login", "/api/regist", "/api/profile/*");
		
		registry.addInterceptor(rateLimitInterceptor)
		.addPathPatterns("/**");
	}
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String address = "http://localhost:5173";
		
		registry.addMapping("/**")
		.allowedOrigins(address)
		.allowedMethods("*")
		.allowCredentials(true);
	}
	
	
	
}
