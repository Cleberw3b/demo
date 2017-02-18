package com.ipayso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CommonBeanConfig {

	@Bean
	public BCryptPasswordEncoder strongEncoder(){
		return  new BCryptPasswordEncoder();
	}
}
