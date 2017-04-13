package com.ipayso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.Customer;
import com.ipayso.model.User;
import com.ipayso.repositories.CustomerRepository;

/**
 * CustomerServiceImpl.class -> This Service offers a CustomerService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	/**
	 * Injects CustomerRepository to make transactions on database
	 * @see CustomerRepository
	 */
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * Injects UserService to use its method 
	 * @see UserService
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * @see CRUDService listAll method
	 */
	@Override
	public List<Customer> listAll() {
		return customerRepository.findAll();
	}
	
	/**
	 * @see CRUDService getById method
	 */
	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	/**
	 * @see CRUDService saveOrUpdate method
	 */
	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return customerRepository.save(domainObject);
	}

	/**
	 * @see CRUDService delete method
	 */
	@Override
	public void delete(Integer id) {
		Customer customer = customerRepository.findOne(id);
		User user = customer.getUser();
		user.setCustomer(null);
		userService.saveOrUpdate(user);
		customerRepository.delete(id);
	}

	/**
	 * @see CustomerService getCustomerByUserName method
	 */
	@Override
	public Customer getCustomerByUserName(String username) {
		return customerRepository.getCustomerByUsername(username);
	}

	/**
	 * @see CustomerService listAll method
	 */
	@Override
	public Page<Customer> listAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
}
