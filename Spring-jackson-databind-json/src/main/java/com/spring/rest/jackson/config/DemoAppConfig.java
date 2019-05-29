package com.spring.rest.jackson.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration			//Replacement of web.xml
@EnableWebMvc			//Replacement of spring-mvc-demo.xml
@ComponentScan(basePackages="com.spring")
public class DemoAppConfig {


	
}
