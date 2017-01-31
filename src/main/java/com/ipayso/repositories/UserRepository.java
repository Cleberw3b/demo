package com.ipayso.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findOneByEmail(String email);

}