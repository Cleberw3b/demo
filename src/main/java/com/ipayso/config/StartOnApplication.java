package com.ipayso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.services.UserService;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Role;
import com.ipayso.util.enums.Years;

/**
 * StartOnApplication.class -> This class implements ApplicationRunner which allow this @Component to be load when the container starts.
 * @author Cleber Oliveira
 * @version 1.0
 * @see ApplicationRunner
 * @see @Component
 */
@Component
public class StartOnApplication implements ApplicationRunner {
	
	private UserService userService;

	/**
	 * Injects an UserService implementation into userService variable
	 * @param userService
	 * @see UserService
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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
		user.setBank("12345");
		user.setBank_acc("12345");
		user.setCountry("Brazil");
		user.setDay(Days.ONE.getDescription());
		user.setMonth(Months.JANUARY.getDescription());
		user.setYear(Years.Year1985.getDescription());
		user.setGender(Genders.MALE.getDescription());
		user.setPassword(encodeService.encodeString("adminPass"));
		user.setEmail("admin@admin.adm");
		user.setEnabled(true);
		user.setRole(Role.ADMIN.getDescription());
		user = userService.saveOrUpdate(user);
	}
}
