package com.ipayso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.User;

/**
 * SecurityService.class -> This interface is a repository for User,  any UserRepository instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see User
 */
public interface UserRepository extends CrudRepository<User, Integer>{
	
	/**
	 * This method request an User passing an e-mail as parameter to receive the user which has the e-mail
	 * @param email as String
	 * @return an User
	 */
	User findOneByEmail(String email);
	
	/**
	 * Find all User and make it pageable
	 * @param pageable
	 * @return Page<User>
	 * @see Pageable
	 */
	Page<User> findAll(Pageable pageable);
}