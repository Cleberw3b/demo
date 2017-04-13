package com.ipayso.util.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.services.MailService;
import com.ipayso.services.RegistrationTokenService;

/**
 * RegistrationListener.class -> This is a Listener implementation to execute every time OnRegistrationCompleteEvent is instantiated 
 * @author Cleber Oliveira
 * @version 1.0
 * @see ApplicationListener
 * @see OnRegistrationCompleteEvent
 * @see @Component
 */
@Component 
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	/**
	 * Injects RegistrationTokenService to send token to confirm e-mail
	 */
	@Autowired
	private RegistrationTokenService registrationTokenService;

	/**
	 * Injects MailService to be able to send e-mails
	 */
    @Autowired
    private MailService mailService;
	
    /**
     * @see ApplicationListener onApplicationEvent method
     */
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);	
	}
	
	/**
	 * This method get the user who was persisted and send an e-mail to confirm User's email
	 * @param event
	 */
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        registrationTokenService.createToken(user, token);
         
        SimpleMailMessage email = mailService.constructConfirmEmailMessage(event.getAppUrl(), event.getLocale(), user, token);
        mailService.sendEmail(email);
    }
}
