package com.ipayso.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;
import com.ipayso.services.MailService;
import com.ipayso.services.RegistrationTokenService;

/**
 * SuccessRegistrationController.class -> This Controller offers a URL filter to map requests for successRegistration page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class SuccessRegistrationController {
	
	/**
	 * Injects an RegistrationTokenService implementation into registrationTokenService variable to send to User
	 * @see RegistrationTokenService
	 */
	@Autowired
	private RegistrationTokenService registrationTokenService;

	/**
	 * Injects an MessageSource implementation into messages variable to get content from our messages.properties
	 * @see MessageSource
	 */
	@Autowired
	private MessageSource messages;

	/**
	 * Injects an MailService implementation into mailService variable to send e-mail as service
	 * @see MailService
	 */
	@Autowired
	private MailService mailService;
	
	/**
	 * When User clicks on the link to activate their account this method is triggered to perform validation about the token they received
	 * whether it is valid or not, case it is valid the method activate the User and them send a confirmation e-mail. 
	 * @param locale
	 * @param token
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration( Locale locale, @RequestParam("token")  String token) throws UnsupportedEncodingException {
		String result = registrationTokenService.validateVerificationToken(token);
		ModelAndView mv = new ModelAndView();
		if (result.equals("valid")) {
			mv.addObject("msg",  messages.getMessage("message.regSucc", null, locale));
			SimpleMailMessage email = mailService.constructConfirmationUserRegistered(locale, registrationTokenService.getByToken(token).getUser());
			mailService.sendEmail(email);
			mv.setViewName("/login");
			return mv;
		}

		mv.addObject("msg", messages.getMessage("auth.message." + result, null, locale));
		mv.addObject("expired", "expired".equals(result));
		mv.addObject("token", token);
		mv.setViewName("/badUser");
		return mv;
	}
	
	/**
	 * In case the user has an invalid this method can send again an e-mail with new token
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resendEmailVerification", method = RequestMethod.POST)
	public ModelAndView resendEmailVerification(@RequestParam("token")  String token, WebRequest request){
		RegistrationToken newVerificationToken = registrationTokenService.generateNewVerificationToken(token);
		String newToken = newVerificationToken.getToken();
		User user =  newVerificationToken.getUser();
		SimpleMailMessage email = mailService.constructResendVerificationTokenEmail(request.getContextPath(), request.getLocale(), newVerificationToken, user);
		mailService.sendEmail(email);
		ModelAndView mv = new ModelAndView();
		mv.addObject("token", newToken);
		return mv;
	}
}
