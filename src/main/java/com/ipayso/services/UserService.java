package com.ipayso.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.User;
import com.ipayso.model.UserRegister;

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
    
    List<User> listAll();
    
    User newRegisteredUser(UserRegister user);
    
    Page<User> listAll(Pageable pageable);
    
    List<String> listAllHasNoCustomer();

}
