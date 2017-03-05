package com.ipayso.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipayso.model.User;
import com.ipayso.repositories.UserRepository;
import com.ipayso.util.enums.Role;

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
     * Injects BCryptPasswordEncoder to encode strings
     * @see BCryptPasswordEncoder
     */
    @Autowired
    private BCryptPasswordEncoder encoderService;
    
    /**
     * Get an User by its e-mail
     * @param email
     * @return User
     */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	/**
	 * List all Users on database
     * @return List
     */
	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
	}
	
	/**
	 * Get an User by its ID
	 * @param id as Integer
     * @return User
     */
	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}
	
	/**
	 * Save or Update an user and return it updated 
	 * @param User
     * @return User
     */
	@Override
	public User saveOrUpdate(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * Save or Update an user and return it updated 
	 * @param User
	 * @return User
	 */
	@Override
	public User newUser(User user) {
		user.setPassword(encoderService.encode(user.getPassword()));
		user.setRole(Role.USER.name());
		return userRepository.save(user);
	}
	
	/**
	 * Delete an User on database
	 * @param id as Integer
     */
	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
		
	}

}
