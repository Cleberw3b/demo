package com.ipayso.controller.admin;

import java.sql.SQLException;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.User;
import com.ipayso.model.util.PageWrapper;
import com.ipayso.services.UserService;
import com.ipayso.services.security.EncoderService;
import com.ipayso.util.enums.Authorisation;

/**
 * UserController.class -> This Controller implements AdminController for User
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 * @see AdminController
 */
@Controller
@RequestMapping(value = "/users")
public class UserController implements AdminController<User>{

	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * Injects an EncoderService implementation into encoderService variable
	 * @see EncoderService
	 */
	@Autowired
	private EncoderService encoderService;

	/**
	 * List all User and put into view
	 */
	@Override
	public ModelAndView list(Pageable pageable, String msg) {
		Page<User> usersPage = userService.listAll(pageable);
        PageWrapper<User> page = new PageWrapper<User>(usersPage, "/users");
		
		ModelAndView mv= new ModelAndView("users");
		mv.addObject("users", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * Persist User
	 */
	@Override
	public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes attributes) {
		
		ModelAndView mv= new ModelAndView("redirect:/users");
		if (result.hasErrors()){
			return add(user);
    	}
		try {
			user.setPassword(encoderService.encodeString(user.getPassword()));
			userService.saveOrUpdate(user);
			
		} catch (Exception e) {
			Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
            	result.addError(new ObjectError("msg", "E-mail already exists"));
            	return add(user);
            }
		}
		String msg = "User " + user.getEmail() + " saved successfully";
		mv.addObject("msg", msg);			
		return mv;
	}

	/**
	 * Call the add form to create new User
	 */
	@Override
	public ModelAndView add(User user) {
		if(user == null){
			user = new User();
		}
		
		ModelAndView mv = new ModelAndView("usersForm");
		mv.addObject("title", "Add New User");
		mv.addObject("roles", Arrays.asList(Authorisation.values()));
		mv.addObject("user", user);
    	return mv;
	}

	/**
	 * Call the edit form to edit User by its id
	 */
	@Override
	public ModelAndView edit(@PathVariable Integer id) {
		User user = userService.getById(id);
		user.setPassword("");
		ModelAndView mv = new ModelAndView("usersForm");
		mv.addObject("title", "Edit User");
		mv.addObject("roles", Arrays.asList(Authorisation.values()));
		mv.addObject("user", user);
    	return mv;
	}

	/**
	 * Delete a User by id
	 */
	@Override
	public ModelAndView delete(@PathVariable Integer id) {
		userService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/users");
		mv.addObject("msg", "User Deleted");
		return mv;
	}
}