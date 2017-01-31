package com.ipayso.local.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ipayso.local.constant.Days;
import com.ipayso.local.constant.Genders;
import com.ipayso.local.constant.Months;
import com.ipayso.local.model.User;
import com.ipayso.local.services.UserService;

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
    	ModelAndView mv = new ModelAndView("redirect:/success-registered/");
    	user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    	userService.saveUser(user);
        return mv;
    }
}