package com.ipayso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * HomeController.class -> This Controller offers a URL filter to map requests for login page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class LoginController {

	/**
	 * Map requests from /login and verify if it contains any errors within request in case positive renders error msg on view
	 * @return login view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("msg", "Your username or password is invalid.");
        return "login";
    }
}