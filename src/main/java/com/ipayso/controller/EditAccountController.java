package com.ipayso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipayso.services.UserService;

@Controller
public class EditAccountController {
	
	private UserService userService;
	private String passwordConfirm;
	
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
  	 }
	
    @RequestMapping(value = "/edit-my-account/{id}",  method = RequestMethod.GET)
    public ModelAndView editUserLogin(@PathVariable Integer id, Model model){
    	ModelAndView mv = new ModelAndView("edit-my-account");
    	model.addAttribute("userLogin", userService.getUserById(id));
        return mv;
    }
    

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
} 