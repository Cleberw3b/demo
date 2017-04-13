package com.ipayso.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ipayso.model.Customer;
import com.ipayso.model.UserRegister;
import com.ipayso.util.formaters.BirthdayFormat;

import groovyjarjarcommonscli.ParseException;

/**
 * UserRegisterToCustomer.class -> This class convert an UserRegister into a Customer.
 * 								   The @Component annotation allow this class to be auto detected on Spring.
 * @author Cleber Oliveira
 * @version 1.0
 * @see Converter
 * @see UserRegister
 * @see Customer
 * @see @Component
 */
@Component
public class UserRegisterToCustomer implements Converter<UserRegister, Customer> {

	/**
	 * Injects BirthdayFormat to convert the strings birthday info into a date
	 */
	@Autowired
	private BirthdayFormat birthdayFormat;
	
	/**
	 * Overrides the convert method passing an UserRegister to be converted into an Customer
	 * @see Converter convert method
	 */
	@Override
	public Customer convert(UserRegister userRegister) {
		Customer customer = new Customer();
		
		if (userRegister != null){
			customer.setGender(userRegister.getGender());
			customer.setCountry(userRegister.getCountry());
			customer.setBank(userRegister.getBank());
			customer.setBank_acc(userRegister.getBank_acc());
			try {
				customer.setBirthday(birthdayFormat.convertRegistrationBirthday(userRegister.getYear(), userRegister.getMonth(), userRegister.getDay()));
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return customer;
	}
}
