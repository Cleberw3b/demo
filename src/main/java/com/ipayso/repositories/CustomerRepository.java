package com.ipayso.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.Customer;

/**
 * CustomerRepository.class -> This interface is a repository for Customer,  any CustomerRepository
 * 							   instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see Customer
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	/**
	 * Find all Customer and make it pageable
	 * @param pageable
	 * @return Page<BugReportTicket>
	 * @see Pageable
	 */
	Page<Customer> findAll(Pageable pageable);

	/**
	 * List all Customer
	 * @return List<Customer>
	 */
	List<Customer> findAll();
	
	/**
	 * This method get a Customer by user name
	 * @param username
	 * @return Customer
	 */
	Customer getCustomerByUsername(String username);
}