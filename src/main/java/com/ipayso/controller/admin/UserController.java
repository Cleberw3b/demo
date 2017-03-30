package com.ipayso.controller.admin;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.User;
import com.ipayso.services.UserService;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.enums.Authorisation;

@Controller
public class UserController {

	/**
	 * TODO
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private EncoderService encoderService;
	
	@RequestMapping(value = "/users",  method = RequestMethod.GET)
	public ModelAndView listUsers(Pageable pageable, @RequestParam(required = false, value = "msg") String msg){
		
		Page<User> usersPage = userService.listAll(pageable);
        PageWrapper<User> page = new PageWrapper<User>(usersPage, "/users");
		
		ModelAndView mv= new ModelAndView("users");
		mv.addObject("users", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		return mv;
	}
	
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid User user, BindingResult result, RedirectAttributes attributes, HttpServletRequest request){		
		ModelAndView mv= new ModelAndView("redirect:/users");
		if (result.hasErrors()){
			return addUser(user);
    	}
		try {
			user.setPassword(encoderService.encodeString(user.getPassword()));
			userService.saveOrUpdate(user);
			
		} catch (Exception e) {
			Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
            	result.addError(new ObjectError("msg", "E-mail already exists"));
            	return addUser(user);
            }
		}
		String msg = "User " + user.getEmail() + " saved successfully";
		mv.addObject("msg", msg);			
		return mv;
	}

	@RequestMapping("/users/add")
	public ModelAndView addUser(User userParam){
		if(userParam == null){
			userParam = new User();			
		}
		
		ModelAndView mv = new ModelAndView("usersForm");
		mv.addObject("title", "Add New User");
		mv.addObject("roles", Arrays.asList(Authorisation.values()));
		mv.addObject("user", userParam);
    	return mv;
	}

	@RequestMapping("users/edit/{id}")
    public ModelAndView editUser(@PathVariable Integer id){
		User user = userService.getById(id);
		user.setPassword("");
		ModelAndView mv = new ModelAndView("usersForm");
		mv.addObject("title", "Edit User");
		mv.addObject("roles", Arrays.asList(Authorisation.values()));
		mv.addObject("user", user);
    	return mv;
    }

	@RequestMapping("users/delete/{id}")
	public ModelAndView deleteUser(@PathVariable Integer id){
		userService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/users");
		mv.addObject("msg", "User Deleted");
		return mv;
	}
}