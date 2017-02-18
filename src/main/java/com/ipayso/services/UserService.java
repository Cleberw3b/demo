package com.ipayso.services;

import com.ipayso.model.User;

public interface UserService extends CRUDService<User>{
	
    User getUserByEmail(String email);

}
