package com.ipayso.local.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ipayso.local.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findOneByEmail(String email);

}