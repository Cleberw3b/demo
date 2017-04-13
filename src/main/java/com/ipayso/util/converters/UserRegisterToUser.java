package com.ipayso.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.model.UserRegister;

/**
 * UserRegisterToUser.class -> This class convert an UserRegister into a User.
 * 							   The @Component annotation allow this class to be auto detected on Spring.
 * @author Cleber Oliveira
 * @version 1.0
 * @see Converter
 * @see UserRegister
 * @see User
 * @see @Component
 */
@Component
public class UserRegisterToUser implements Converter<UserRegister, User> {

	/**
	 * Overrides the convert method passing an UserRegister to be converted into an User
	 * @see Converter convert method
	 */
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
