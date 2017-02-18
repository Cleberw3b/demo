package com.ipayso.util.validators;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * EmailValidator.class -> Here we offer common beans @Configuraion allow the container to instantiate this beans without using XML
 * @author Cleber Oliveira
 * @version 1.0
 * @see ConstraintValidator
 * @see ValidEmail
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	
	/**
	 * Create an Annotation to ValidEmail 
	 */
	@Override
	public void initialize(ValidEmail constraintAnnotation) {

	}
	
	/**
	 * Override isValid  using  validateEmail() method to validate String
	 */
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context){
		return (validateEmail(email));
	}
	
	/**
	 * Create InternetAddress object and validated the supplied
	 * address which in this case is an email address.
	 * @return true if String is a valid email
	 * 		   false if it is not
	 * @see InternetAddress
	 * @throws AddressException
	 */
	private boolean validateEmail(String email) {
		boolean isValid = false;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;

	}
}