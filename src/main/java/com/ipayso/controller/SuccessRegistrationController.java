package com.ipayso.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String confirmRegistration( Locale locale,  Model model, @RequestParam("token")  String token) throws UnsupportedEncodingException {
		String result = registrationTokenService.validateVerificationToken(token);
		if (result.equals("valid")) {
			model.addAttribute("msg",  messages.getMessage("message.regSucc", null, locale));
			return "redirect:/login";
		}

		model.addAttribute("msg", messages.getMessage("auth.message." + result, null, locale));
		model.addAttribute("expired", "expired".equals(result));
		model.addAttribute("token", token);
		return "redirect:/badUser.html?lang=" + locale.getLanguage();
	}
}
