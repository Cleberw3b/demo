package com.ipayso.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.Customer;

/**
 * CustomerRepository.class -> This interface is a repository for Customer,  any CustomerRepository instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see Customer
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	/**
	 * This method request an User passing an e-mail as parameter to receive the user which has the e-mail
	 * @param email as String
	 * @return an User
	 */
	
	Page<Customer> findAll(Pageable pageable);

	List<Customer> findAll();

	Customer getCustomerByUsername(String username);

}