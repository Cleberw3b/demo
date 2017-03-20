package com.ipayso.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.Customer;

/**
 * CustomerService.class -> This interface extends all methods from CRUDService and assign a 
 * specific method to get an Customer by its email and username
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see Customer
 */
public interface CustomerService extends CRUDService<Customer>{
	
	/**
	 * Get an User by its email
	 * @param email
	 * @return User
	 */
	Customer getCustomerByUserName(String username);
	
    Page<Customer> listAll(Pageable pageable);

}
