package com.ipayso.services;

import java.util.Locale;

import org.springframework.mail.SimpleMailMessage;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;

/**
 * MailService.class -> This interface signs what the MailService must do
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface MailService {
	

	/**
	 * This method sends an email with a token to users confirm their e-mail account
	 * @param appURL
	 * @param locale
	 * @param user
	 * @param token
	 * @return SimpleMailMessage
	 */
	SimpleMailMessage constructConfirmEmailMessage(final String appURL, final Locale locale, final User user, final String token);
	
	/**
	 * In case of invalid token or expiration the user can call this method to send a new confirmation token
	 * @param contextPath
	 * @param locale
	 * @param newToken
	 * @param user
	 * @return SimpleMailMessage
	 */
	SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, 
															final RegistrationToken newToken, final User user);
	
	/**
	 * This method is called to reset password
	 * @param contextPath
	 * @param locale
	 * @param token
	 * @param user
	 * @return SimpleMailMessage
	 */
	SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user);
	
	
	/**
	 * After user confirm their e-mail this method will send a message of this confirmation
	 * @param locale
	 * @param user
	 * @return SimpleMailMessage
	 */
	SimpleMailMessage constructConfirmationUserRegistered(final Locale locale, final User user);
	
	/**
	 * Simple method to send emails
	 * @param email
	 */
	void sendEmail(SimpleMailMessage email);
	
}
