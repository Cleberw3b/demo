package com.ipayso.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.Customer;
import com.ipayso.model.User;
import com.ipayso.model.UserRegister;
import com.ipayso.repositories.UserRepository;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.converters.UserRegisterToCustomer;
import com.ipayso.util.converters.UserRegisterToUser;
import com.ipayso.util.enums.Authorisation;

/**
 * UserServiceImpl.class -> This Service offers a UserService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService{
	
	/**
	 * Injects UserRepository to make transactions on database
	 * @see UserRepository
	 */
	@Autowired
	private UserRepository userRepository;
	
	 /**
     * Injects EncoderService to encode strings
     * @see EncoderService
     */
    @Autowired
    private EncoderService encoderService;
    
	 /**
     * Injects UserRegisterToUser to convert UserRegister into User
     * @see UserRegisterToUser
     */
    @Autowired
    private UserRegisterToUser userRegisterToUser;
    
	 /**
     * Injects UserRegisterToCustomer to convert UserRegister into Customer
     * @see UserRegisterToCustomer
     */
    @Autowired
    private UserRegisterToCustomer userRegisterToCustomer;
    
    /**
	 * @see UserService getUserByEmail method
	 */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	/**
	 * @see CRUDService listAll method
	 */
	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
	}
	
	/**
	 * @see CRUDService getById method
	 */
	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}
	
	/**
	 * @see CRUDService saveOrUpdate method
	 */
	@Override
	public User saveOrUpdate(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * @see CRUDService delete method
	 */
	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
		
	}
	
	/**
	 * @see UserService listAll method
	 */
	@Override
	public Page<User> listAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	/**
	 * @see UserService newRegisteredUser method
	 */
	@Override
	public User newRegisteredUser(UserRegister userRegister) {
		User user = userRegisterToUser.convert(userRegister);
		Customer customer = userRegisterToCustomer.convert(userRegister);
		user.setCustomer(customer);
		user.setPassword(encoderService.encodeString(user.getPassword()));
		user.setRole(Authorisation.USER.name());
		user.setEnabled(false);
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		
		return saveOrUpdate(user);
	}
	
	/**
	 * @see UserService listAllHasNoCustomer method
	 */
	@Override
	public List<String> listAllHasNoCustomer(){
		List<String> usersEmail = new ArrayList<>();
		List<User> users = listAll();
		for (User user : users){
			if(user.getCustomer() == null){
				usersEmail.add(user.getEmail());
			}
		}
		return usersEmail;
	}
}
