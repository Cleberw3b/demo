package com.ipayso.util.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    											
	
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
	 * Use a Regex pattern to validate a string as e-mail
	 * @return true if String is a valid email
	 * 		   false if it is not
	 * @see Pattern
	 */
	private boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
	}
}