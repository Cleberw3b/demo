package com.ipayso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.services.UserService;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.enums.Authorisation;

/**
 * StartOnApplication.class -> This class implements ApplicationRunner which allow this @Component to be load when the container starts.
 * @author Cleber Oliveira
 * @version 1.0
 * @see ApplicationRunner
 * @see @Component
 */
@Component
public class StartOnApplication implements ApplicationRunner {
	
	/**
	 * Injects an UserService implementation into userService variable
	 * @param userService
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private EncoderService encodeService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		setAdminUser();
	}
	
	/**
	 * Set an Administrative user on start
	 */
	private void setAdminUser(){
		User user = new User();
		user.setPassword(encodeService.encodeString("adminPass"));
		user.setEmail("admin@ipayso.com");
		user.setEnabled(true);
		user.setRole(Authorisation.ADMIN.getDescription());
		user = userService.saveOrUpdate(user);
	}
}
