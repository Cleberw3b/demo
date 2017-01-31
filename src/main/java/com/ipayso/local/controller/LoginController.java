package com.ipayso.local.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.local.model.Login;
import com.ipayso.local.model.User;
import com.ipayso.local.services.UserService;

@Controller
public class LoginController {

	private UserService userService;

	
	
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
  	 }
	
    // Login form with error

    @RequestMapping("/login")
    public ModelAndView Login(Login login){
    	ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    
    @RequestMapping("/login-error.html")
    public ModelAndView loginError() {
    	ModelAndView mv = new ModelAndView("/login");
    	mv.addObject("loginError", true);
    	return mv;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView ValidLoginUser(@Valid Login login, BindingResult userResult, RedirectAttributes attributes){
    	User user = userService.getUserByEmail(login.getEmail());
    	if(user == null || !user.getPassword().equals(login.getPassword())){
    		attributes.addFlashAttribute("msg","E-mail or Password invalid");
    		return Login(login);    		
    	}

    	ModelAndView mv = new ModelAndView("redirect:/home?login=sucess");
    	userService.saveUser(user);
        return mv;
    }
    
}