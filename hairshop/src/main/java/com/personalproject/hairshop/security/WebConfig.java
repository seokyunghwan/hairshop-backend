package com.personalproject.hairshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 요청에 허용하고 싶은 경우
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
			.addResourceLocations("file:src/main/webapp/resources/");
//			.addResourceLocations("/static/");
	}
}
