package com.ipayso.services;

import java.util.Locale;

import org.springframework.mail.SimpleMailMessage;

import com.ipayso.model.User;
import com.ipayso.model.security.VerificationToken;

/**
 * VerificationTokenService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface MailService {
	
	SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, 
															final VerificationToken newToken, final User user);
	
	SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user);
	
}
