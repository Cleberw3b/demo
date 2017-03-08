package com.ipayso.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

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
	
	@Bean
    public ResourceBundleMessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("i18n/messages");
    	messageSource.setDefaultEncoding("UTF-8");
    	return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
    	CookieLocaleResolver resolver = new CookieLocaleResolver();
    	resolver.setDefaultLocale(new Locale("en"));
    	resolver.setCookieName("myLocaleCookie");
    	resolver.setCookieMaxAge(4800);
    	return resolver;
    }
}
