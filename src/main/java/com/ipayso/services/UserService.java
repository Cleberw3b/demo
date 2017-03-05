package com.ipayso.services;

import com.ipayso.model.User;

/**
 * UserService.class -> This interface extends all methods from CRUDService and assign a specific method to get an User by its email
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see User
 */
public interface UserService extends CRUDService<User>{
	
	/**
	 * Get an User by its email
	 * @param email
	 * @return User
	 */
    User getUserByEmail(String email);
    
    User newUser(User user);

}
