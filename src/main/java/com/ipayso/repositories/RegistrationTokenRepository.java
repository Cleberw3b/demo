package com.ipayso.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.RegistrationToken;

/**
 * RegistrationTokenRepository.class -> This interface is a repository for RegistrationToken,  any UserRepository instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see RegistrationToken
 */
public interface RegistrationTokenRepository extends CrudRepository<RegistrationToken, Integer>{

	RegistrationToken findByToken(String token);

}