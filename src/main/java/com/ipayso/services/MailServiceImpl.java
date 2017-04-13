package com.ipayso.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;

/**
 * MailServiceImpl.class -> This class control all the e-mails this application needs to operate
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Service
 * @see MailService
 */
@Service
public class MailServiceImpl implements MailService {
	
	/**
	 * This is a prefix for construct URLs
	 */
	private final String URLprefix = "http://localhost:8080";

	/**
	 * Injects JavaMailSender to send email 
	 */
    @Autowired
    private JavaMailSender mailSender;
	
    /**
     * Injects MessageSource to capture messages from message.properties
     */
	@Autowired
	private MessageSource messages;
	
	/**
	 * Injects Environment to get the e-mail from configuration.properties
	 */
    @Autowired
    private Environment env;

    /**
    * @see MailService sendEmail method
    */
    @Override
    public void sendEmail(SimpleMailMessage email){
    	try {
    		mailSender.send(email);			
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    /**
    * @see MailService constructConfirmEmailMessage method
    */
    @Override
    public SimpleMailMessage constructConfirmEmailMessage(final String appURL, final Locale locale, final User user, final String token) {
    	final String subject = "Registration Confirmation";
    	String confirmationUrl = URLprefix + appURL + "/emailConfirm.html?token=" + token;
    	String message = messages.getMessage("message.regConfirmEmail", null, locale);
    	String messageDisconsiderEmail = messages.getMessage("message.disconsiderEmail", null, locale);
    	
    	return constructSendEmail(subject, message + "\r\n\n" + confirmationUrl + "\r\n\n" + messageDisconsiderEmail, user.getEmail());
    }
	
    /**
    * @see MailService constructResendVerificationTokenEmail method
    */
	@Override
	public SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final RegistrationToken newToken, final User user) {
		final String subject = "Resend Registration Token";
		final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructSendEmail(subject, message + " \r\n" + confirmationUrl, user.getEmail());
    }

    /**
    * @see MailService constructResetTokenEmail method
    */
	@Override
    public SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
		final String subject = "Reset Password";
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructSendEmail(subject, message + " \r\n" + url, user.getEmail());
    }
	
    /**
    * @see MailService constructConfirmationUserRegistered method
    */
	@Override
	public SimpleMailMessage constructConfirmationUserRegistered(final Locale locale, final User user) {
		final String subject = "You have registered on I Pay So";
		final String message = messages.getMessage("message.regSucc", null, locale);
        return constructSendEmail(subject, message + " \r\n", user.getEmail());
	}
	
	/**
	 * This method construct the structure to send an email
	 * @param subject
	 * @param body
	 * @param recipientAddress
	 * @return SimpleMailMessage
	 */
    private SimpleMailMessage constructSendEmail(String subject, String body, String recipientAddress) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(recipientAddress);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
