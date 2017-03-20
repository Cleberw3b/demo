package com.ipayso.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ipayso.model.Customer;
import com.ipayso.model.UserRegister;
import com.ipayso.util.formaters.BirthdayFormat;

import groovyjarjarcommonscli.ParseException;

@Component
public class UserRegisterToCustomer implements Converter<UserRegister, Customer> {

	@Autowired
	private BirthdayFormat birthdayFormat;
	
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
