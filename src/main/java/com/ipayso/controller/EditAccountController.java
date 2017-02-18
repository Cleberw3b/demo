package com.ipayso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipayso.services.UserService;

@Controller
public class EditAccountController {
	
	private UserService userService;
	
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
  	 }
	
    @RequestMapping(value = "/editMyAccount/{id}",  method = RequestMethod.GET)
    public ModelAndView editUserLogin(@PathVariable Integer id){
    	ModelAndView mv = new ModelAndView("editMyAccount");
    	mv.addObject("userLogin", userService.getById(id));
        return mv;
    }
} 