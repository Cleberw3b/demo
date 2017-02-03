package com.ipayso.controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.constant.Days;
import com.ipayso.constant.Genders;
import com.ipayso.constant.Months;
import com.ipayso.model.User;
import com.ipayso.services.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class SignUpController {
	
	private UserService userService;
	
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
  	 }
	
    @RequestMapping(value = "/signup",  method = RequestMethod.GET)
    public ModelAndView newSignUp(User user){
    	ModelAndView mv = new ModelAndView("signup");
    	mv.addObject("genders", Arrays.asList(Genders.values()));
    	mv.addObject("months", Arrays.asList(Months.values()));
    	mv.addObject("days", Arrays.asList(Days.values()));
    	mv.addObject("passwordConfirm", "");
        return mv;
    }
    
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid User user, BindingResult userResult, RedirectAttributes attributes){
    	if (!user.getPassword().equals(user.getPasswordConfirm())){
    		userResult.addError(new ObjectError("msg", "Password must match"));
    	}
    	if (userResult.hasErrors()){
			return newSignUp(user);
    	}
    	
    	user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    	try {
    		user =  userService.saveUser(user);
		} catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
				userResult.addError(new ObjectError("msg", "E-mail already exists"));
				return newSignUp(user);
            }
		}
    	ModelAndView mv = new ModelAndView("redirect:/success-registered/" + String.valueOf(user.getId()));
    	return mv;
    }
    
    @RequestMapping("/success-registered/{id}")
    public String sucess(@PathVariable Integer id, Model model){
    	model.addAttribute("user", userService.getUserById(id));
    	return "/success-registered";
    }
   
}