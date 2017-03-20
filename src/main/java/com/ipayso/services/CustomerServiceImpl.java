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
 * UserServiceImpl.class -> This Service offers a UserService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see CustomerService
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Customer> listAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return customerRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		Customer customer = customerRepository.findOne(id);
		User user = customer.getUser();
		user.setCustomer(null);
		userService.saveOrUpdate(user);
		customerRepository.delete(id);
	}

	@Override
	public Customer getCustomerByUserName(String username) {
		return customerRepository.getCustomerByUsername(username);
	}

	@Override
	public Page<Customer> listAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	/**
	 * TODO
	 */
	

}
