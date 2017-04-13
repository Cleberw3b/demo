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
	 * Find all User
	 * @return List<User>
	 */
    List<User> listAll();
	
	/**
	 * Get an User by its email
	 * @param email
	 * @return User
	 */
    User getUserByEmail(String email);
    
    /**
     * Register a new user
     * @param user
     * @return User persisted
     */
    User newRegisteredUser(UserRegister user);
    
	/**
	 * Find all Customer and make it pageable
	 * @param pageable
	 * @return Page<Customer>
	 * @see Pageable
	 */
    Page<User> listAll(Pageable pageable);

    /**
     * List all Users that has no Customer bound
     * @return
     */
    List<String> listAllHasNoCustomer();
}
