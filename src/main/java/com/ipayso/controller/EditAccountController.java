package com.ipayso.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.User;
import com.ipayso.services.UserService;
import com.ipayso.services.security.SecurityService;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Years;

/**
 * HomeController.class -> This Controller offers a URL filter to map requests for home page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class EditAccountController {

	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * Injects an SecurityService implementation into securityService variable
	 * @see SecurityService
	 */
	@Autowired
	private SecurityService securityService;

	/**
	 * When filter captures a /editMyAccount URL request this method is called to get the authenticated user and add objects on the view side
	 * @return editMyAccount view
	 */
	@RequestMapping(value = "/editMyAccount",  method = RequestMethod.GET)
	public ModelAndView editUserLogin(User userParam){
		ModelAndView mv = new ModelAndView("editMyAccount");
		User user = userParam;
		if(user == null || user.getEmail() == null || user.getEmail().isEmpty()){
			user = userService.getUserByEmail(securityService.findLoggedInUsername());
		}
		user.setPassword("");
		mv.addObject("user", user);
		mv.addObject("genders", Arrays.asList(Genders.values()));
		mv.addObject("months", Arrays.asList(Months.values()));
		mv.addObject("days", Arrays.asList(Days.values()));
		mv.addObject("years", Arrays.asList(Years.values()));
		mv.addObject("countries", Arrays.asList(Countries.values()));
		return mv;
	}

	/**
	 * When editMyAccount form is sent to action this method execute a validation for User,
	 * if some error is caught we use a result to add errors messages and show it on view. After all the validation,
	 * updates user on database and redirect to success page.
	 * @param user
	 * @param userResult
	 * @param attributes
	 * @return
	 * @see ModelAndView
	 * @see @RequestMapping
	 */
	@RequestMapping(value = "/editMyAccount", method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid User user, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()){
			return editUserLogin(user);
		}
		user =  userService.saveOrUpdate(user);
		ModelAndView mv = new ModelAndView("redirect:/success");
		return mv;
	}
} 