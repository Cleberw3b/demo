package com.ipayso.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * CommonBeanConfig.class -> Here we offer common beans @Configuraion allow the container to instantiate this beans without using XML.
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Configuration
 */
@Configuration
public class CommonBeanConfig {

	/**
	 * Make BCryptPasswordEncoder a bean to encrypt password and verify the matches. 
	 * @return BCryptPasswordEncoder
	 * @see BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder strongEncoder(){
		return  new BCryptPasswordEncoder();
	}
	
	/**
	 * This bean is a reference for all messages on the folder i18n and begin with messages
	 * We can make internalization using this reference.
	 * @return ResourceBundleMessageSource
	 * @see ResourceBundleMessageSource
	 */
	@Bean
    public ResourceBundleMessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("i18n/messages");
    	messageSource.setDefaultEncoding("UTF-8");
    	return messageSource;
    }
    
	/**
	 * This bean resolves locale for internalization and set default the EN-Locale
	 * @return LocaleResolver
	 * @see LocaleResolver
	 */
    @Bean
    public LocaleResolver localeResolver() {
    	CookieLocaleResolver resolver = new CookieLocaleResolver();
    	resolver.setDefaultLocale(new Locale("en"));
    	resolver.setCookieName("myLocaleCookie");
    	resolver.setCookieMaxAge(4800);
    	return resolver;
    }
    
    /**
     * Make the HttpSessionEventPublisher a bean to get the event on http session giving access to the data
     * that is transport through the protocol
     * @return HttpSessionEventPublisher
     * @see HttpSessionEventPublisher
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
    	return new HttpSessionEventPublisher();
    }
}
