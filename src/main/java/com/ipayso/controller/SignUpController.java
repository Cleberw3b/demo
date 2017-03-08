package com.ipayso.controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.User;
import com.ipayso.services.UserService;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Years;
import com.ipayso.util.event.OnRegistrationCompleteEvent;

/**
 * SignUpController.class -> This Controller offers a URL filter to map requests for signup page 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class SignUpController {
	
	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;
    
	/**
	 * Injects an ApplicationEventPublisher implementation to notify listeners
	 * @see ApplicationEventPublisher
	 */
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
    /**
     * When filter captures a signup URL request this method is called to add objects on the view side 
     * @param user
     * @return
     * @see ModelAndView
     * @see @RequestMapping
     */
    @RequestMapping(value = "/signup",  method = RequestMethod.GET)
    public ModelAndView newSignUp(User user){
    	ModelAndView mv = new ModelAndView("signup");
    	mv.addObject("genders", Arrays.asList(Genders.values()));
    	mv.addObject("months", Arrays.asList(Months.values()));
    	mv.addObject("days", Arrays.asList(Days.values()));
    	mv.addObject("years", Arrays.asList(Years.values()));
    	mv.addObject("countries", Arrays.asList(Countries.values()));
    	mv.addObject("passwordConfirm", "");
        return mv;
    }
    
    /**
     * When signup form is sent to action this method execute a password confirmation and validate the User,
     * if some error is caught, errors messages will be displayed on view. After all the validation
     * we try to save this new user on database, in case of an existent e-mail it will catch a exception and say e-mail
     * already registered. When registered the user will be auto logged and redirected to an successful page.
     * @param user
     * @param userResult
     * @param attributes
     * @return
     * @see ModelAndView
     * @see @RequestMapping
     */
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid User user, BindingResult userResult, RedirectAttributes attributes, WebRequest request){
    	if (!user.getPassword().equals(user.getPasswordConfirm())){
    		userResult.addError(new ObjectError("msg", "Password must match"));
    	}
    	if (userResult.hasErrors()){
			return newSignUp(user);
    	}
    	try {
    		user =  userService.newUser(user);
    		eventPublisher.publishEvent(new OnRegistrationCompleteEvent (user, request.getLocale(), request.getContextPath()));
		} catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
				userResult.addError(new ObjectError("msg", "E-mail already exists"));
				return newSignUp(user);
            }
		}
    	return successRegistration(user);
    }
    
	@RequestMapping(value = "/success" , method = RequestMethod.GET)
	public ModelAndView successRegistration(User user){
		ModelAndView mv = new ModelAndView("successAfterSignUp");
		mv.addObject("user", user);
		return mv;
	}
}