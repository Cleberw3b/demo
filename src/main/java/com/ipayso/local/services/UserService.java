package com.ipayso.local.services;

import com.ipayso.local.model.User;

public interface UserService {
	Iterable<User> listAllUsers();

    User getUserById(Integer id);
    
    User getUserByEmail(String email);

    User saveUser(User userLogin);

    void deleteUser(Integer id);
}
