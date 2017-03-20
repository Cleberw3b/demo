package com.ipayso.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipayso.model.User;
import com.ipayso.services.UserService;

/**
 * UserDetailsServiceImpl.class -> This Service offers a UserDetailsService implementation to get the a userDetails by it's login,
 * 							 	   which in this case the e-mail address is the reference.
 * @author Cleber Oliveira
 * @version 1.0
 * @see UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * Injects UserService in order to use its methods
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * Injects userToUserDetails by @Qualifier to indicate which class should implement the Converter interface,
	 * in order to convert a User into a Userdetails
	 * @see UserToUserDetails
	 * @see User
	 * @see Userdetails
	 */
	@Autowired
	@Qualifier(value = "userToUserDetails")
	private Converter<User, UserDetails> userUserDetailsConverter;
	

	/**
	 * This method implements loadUserByUsername to get an UserDetails using e-mail as parameter which
	 * will be processed by userService, them converted into a UserDetails to be returned and this method
	 * is annotated @Transactional to only read on DB.
	 * @see @Transactional
	 * @throws UsernameNotFoundException
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userUserDetailsConverter.convert(userService.getUserByEmail(email));
	}

}
