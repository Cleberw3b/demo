package com.ipayso.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.model.UserRegister;

@Component
public class UserRegisterToUser implements Converter<UserRegister, User> {

	@Override
	public User convert(UserRegister userRegister) {
		User user = new User();
		
		if (userRegister != null){
			user.setEmail(userRegister.getEmail());
			user.setPassword(userRegister.getPassword());
		}
		
		return user;
	}
}
