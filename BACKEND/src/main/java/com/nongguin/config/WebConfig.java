package com.nongguin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nongguin.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**","/match/search/date/**","/match/search/condition","/match/search/detail/**","/swagger-resources/**","/swagger-ui/**","/v2/api-docs");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST","PUT","DELETE");
	}
}
