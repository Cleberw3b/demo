package com.ipayso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * HomeController.class -> This Controller offers a URL filter to map requests for login page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class LoginController {

    /**
     * Injects MessageSource to capture messages from message.properties
     */
	@Autowired
	private MessageSource messages;
	
	/**
	 * Map requests from /login and verify if it contains any errors within request in case positive renders error msg on view
	 * @return login view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, WebRequest request) {
        if (error != null)
            model.addAttribute("msg", messages.getMessage("auth.message.invalidUser", null, request.getLocale()));
        	System.out.println(error);
        return "login";
    }
}