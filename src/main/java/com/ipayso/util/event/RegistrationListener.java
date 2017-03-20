package com.ipayso.util.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ipayso.model.User;
import com.ipayso.services.MailService;
import com.ipayso.services.RegistrationTokenService;

@Component 
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	/**
	 * 
	 */
	@Autowired
	private RegistrationTokenService registrationTokenService;

    @Autowired
    private MailService mailService;
	
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
         
        SimpleMailMessage email = mailService.constructConfirmEmailMessage(event.getAppUrl(), event.getLocale(), user, token);
        mailService.sendEmail(email);
    }
}
