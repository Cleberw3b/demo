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


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Converter<User, UserDetails> userUserDetailsConverter;

	@Autowired
	@Qualifier(value = "userToUserDetails")
	public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
		this.userUserDetailsConverter = userUserDetailsConverter;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userUserDetailsConverter.convert(userService.getUserByEmail(email));
	}

}
