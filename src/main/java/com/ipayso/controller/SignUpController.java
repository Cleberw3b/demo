package com.ipayso.controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.User;
import com.ipayso.model.UserRegister;
import com.ipayso.services.UserService;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Years;
import com.ipayso.util.event.OnRegistrationCompleteEvent;
import com.ipayso.util.event.RegistrationListener;

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
     * Injects MessageSource to capture messages from message.properties
     */
	@Autowired
	private MessageSource messages;
	
    /**
     * When filter captures a sign up URL request this method is called to add objects on the view side 
     * @param user
     * @return
     * @see ModelAndView
     * @see @RequestMapping
     */
    @RequestMapping(value = "/signup",  method = RequestMethod.GET)
    public ModelAndView newSignUp(UserRegister userRegister){
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
     * When sign up form is sent to action this method execute a password confirmation and validate the User,
     * if some error is caught, errors messages will be displayed on view. After all the validation
     * it tries to save this new user on database, in case of an existent e-mail it will catch a exception and say e-mail
     * already registered. In case of success it will publish an event called OnRegistrationCompleteEvent that will wake
     * @RegistrationListener to perform token creation and email sent.
     * @param user
     * @param userResult
     * @param attributes
     * @return
     * @see ModelAndView
     * @see @RequestMapping
     * @see OnRegistrationCompleteEvent
     * @see RegistrationListener
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid UserRegister userRegister, BindingResult result, RedirectAttributes attributes, WebRequest request){
    	
    	User user = new User();
    	
    	if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())){
    		result.addError(new ObjectError("msg", messages.getMessage("error.PasswordMatches.user", null, request.getLocale())));
    	}
    	if (result.hasErrors()){
			return newSignUp(userRegister);
    	}
    	try {
    		user =  userService.newRegisteredUser(userRegister);
    		eventPublisher.publishEvent(new OnRegistrationCompleteEvent (user, request.getLocale(), request.getContextPath()));
		} catch (RuntimeException e) {
			//TODO Tratar a excessao para emails duplicados
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
            	result.addError(new ObjectError("msg", messages.getMessage("error.UniqueUsername.email", null, request.getLocale())));
				return newSignUp(userRegister);
            }
		}
    	return successRegistration(user);
    }
    
    /**
     * This method maps requests coming from /success to its view adding the User who has been successfully registered
     * @param user
     * @return
     */
	@RequestMapping(value = "/success" , method = RequestMethod.GET)
	public ModelAndView successRegistration(User user){
		ModelAndView mv = new ModelAndView("successAfterSignUp");
		mv.addObject("user", user);
		return mv;
	}
}