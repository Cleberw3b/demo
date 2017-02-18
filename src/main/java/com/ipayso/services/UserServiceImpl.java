package com.ipayso.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipayso.model.User;
import com.ipayso.repositories.UserRepository;
import com.ipayso.util.enums.Role;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
  	 }
    
    private BCryptPasswordEncoder encoderService;

    @Autowired
    public void setEncryptionService(BCryptPasswordEncoder encryptionService) {
        this.encoderService = encryptionService;
    }
    
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User user) {
		if(user.getPassword() != null){
			user.setPassword(encoderService.encode(user.getPassword()));
        }
		user.setRole(Role.USER.name());
		return userRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
		
	}

}
