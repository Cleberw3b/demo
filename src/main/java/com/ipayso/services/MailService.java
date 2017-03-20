package com.ipayso.services;

import java.util.Locale;

import org.springframework.mail.SimpleMailMessage;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;

/**
 * VerificationTokenService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface MailService {
	
	SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, 
															final RegistrationToken newToken, final User user);
	
	SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user);
	
	SimpleMailMessage constructConfirmEmailMessage(final String appURL, final Locale locale, final User user, final String token);
	
	SimpleMailMessage constructConfirmationUserRegistered(final Locale locale, final User user);
	
	void sendEmail(SimpleMailMessage email);
	
}
