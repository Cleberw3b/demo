package com.ipayso.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;
import com.ipayso.model.security.VerificationToken;
import com.ipayso.services.RegistrationTokenService;

/**
 * SuccessRegistrationController.class -> This Controller offers a URL filter to map requests for successRegistration page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class SuccessRegistrationController {

	
	@Autowired
	private RegistrationTokenService registrationTokenService;

	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration( Locale locale, @RequestParam("token")  String token) throws UnsupportedEncodingException {
		String result = registrationTokenService.validateVerificationToken(token);
		ModelAndView mv = new ModelAndView();
		if (result.equals("valid")) {
			mv.addObject("msg",  messages.getMessage("message.regSucc", null, locale));
			mv.setViewName("/login");
			return mv;
		}

		mv.addObject("msg", messages.getMessage("auth.message." + result, null, locale));
		mv.addObject("expired", "expired".equals(result));
		mv.addObject("token", token);
		mv.setViewName("/badUser");
		return mv;
		//return "redirect:/badUser.html?lang=" + locale.getLanguage();
	}
	
	@RequestMapping(value = "/resendEmailVerification", method = RequestMethod.POST)
	public ModelAndView resendEmailVerification(){
		RegistrationToken newVerificationToken = registrationTokenService.generateNewVerificationToken("");
		String newToken = newVerificationToken.getToken();
		User user =  newVerificationToken.getUser();
		//TODO resend link to confirm e-mail
		ModelAndView mv = new ModelAndView();
		mv.addObject("token", newToken);
		return mv;
	}
}
