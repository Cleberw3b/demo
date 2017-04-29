package com.ipayso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ipayso.model.Account;
import com.ipayso.model.Customer;
import com.ipayso.model.User;
import com.ipayso.services.MailService;
import com.ipayso.services.UserService;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.enums.Authorisation;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Genders;

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

	@Autowired
	private MailService mailService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		setAdminUser();
	}

	/**
	 * Set an Administrative user on start
	 */
	protected User setAdminUser(){
		User user = new User();
		user.setPassword(encodeService.encodeString("adminPass"));
		user.setEmail("admin@ipayso.com");
		user.setEnabled(true);
		user.setRole(Authorisation.ADMIN.getDescription());
		return userService.saveOrUpdate(user);
	}
	
	protected void setAccount(User user){
		Account acc = new Account(300, 9999);
		Customer customer = new Customer();
		customer.setAccount(acc);
		customer.setGender(Genders.MALE.getDescription());
		customer.setCountry(Countries.BR.getDescription());
		customer.setBank("1234");
		customer.setBank_acc("1234");
		user.setCustomer(customer);
		userService.saveOrUpdate(user);
	}
	
	protected void testEmailConectivity(){
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Teste Connectivity");
		email.setText("Simple Test");
		email.setTo("ipaysodev@gmail.com");
		email.setFrom("ipaysodev@gmail.com");
		mailService.sendEmail(email);
	}
}
