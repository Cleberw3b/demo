package com.ipayso.util.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.services.RegistrationTokenService;

@Component 
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	/**
	 * 
	 */
	@Autowired
	private RegistrationTokenService registrationTokenService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);	
	}
	
	/**
	 * 
	 * @param event
	 */
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        registrationTokenService.createToken(user, token);
         
        SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

	/**
	 * 
	 * @param event
	 * @param user
	 * @param token
	 * @return
	 */
    private SimpleMailMessage constructEmailMessage(OnRegistrationCompleteEvent event, User user, String token) {
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl ="http://localhost:8080" + event.getAppUrl() + "/emailConfirm.html?token=" + token;
        String message = "You registered successfully. We will send you a confirmation message to your email account.";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
