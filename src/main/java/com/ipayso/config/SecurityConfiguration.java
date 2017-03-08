package com.ipayso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * SecurityConfiguration.class -> Here @Configuraion allows the container set security features without using XML
 * 								  Enabling WebSecurity we say that this class will implement WebSecurityConfigurerAdapter
 * 								  It sets the authorization on the URL requests, besides it has the authentication method
 * 								  to authorize users.
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Configuration
 * @see @EnableWebSecurity
 * @see WebSecurityConfigurerAdapter
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 
    private UserDetailsService userDetailsService;
	
    /**
	 * Inject userDetailsService to access it's methods
	 * @see UserDetailsService
	 */
    @Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

    /**
	 * Inject authenticationManagerBuilder to access it's methods and makes UserDetailsService as reference for authenticate users as UserDetails
	 * @see AuthenticationManagerBuilder
	 * @see BCryptPasswordEncoder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * Override configure from WebSecurityConfigurerAdapter and describes authorization permitting request
	 * unauthenticated for the Matchers and any other must be authenticated referring to login page
	 * @see HttpSecurity
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeRequests().antMatchers("/", "/home", "/bugReport", "/signup", "/success", "/emailConfirm**","/badUser**", "/resources/**", "/css/**", "/js/**", "/fonts/**", "/images/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
        .and()
        .logout().permitAll();
	}

}
