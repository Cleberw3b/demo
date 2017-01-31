package com.ipayso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
	        	.authorizeRequests().antMatchers("/", "/home", "/login", "/signup").permitAll()
	        .and()
		        .formLogin()
		        .loginPage("/login")
		        .failureUrl("/login-error")
		    .and()
		        .logout()
		        .logoutSuccessUrl("/home");
    }
 
}