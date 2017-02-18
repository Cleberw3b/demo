package com.ipayso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * CommonBeanConfig.class -> Here we offer common beans @Configuraion allow the container to instantiate this beans without using XML
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Configuration
 */
@Configuration
public class CommonBeanConfig {

	/**
	 * Make BCryptPasswordEncoder a bean
	 * @see BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder strongEncoder(){
		return  new BCryptPasswordEncoder();
	}
}
