package com.ipayso.controller.admin;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

import com.ipayso.model.Customer;
import com.ipayso.model.User;
import com.ipayso.model.util.PageWrapper;
import com.ipayso.services.CustomerService;
import com.ipayso.services.UserService;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Role;
import com.ipayso.util.enums.Years;
import com.ipayso.util.formaters.BirthdayFormat;

/**
 * CustomerController.class -> This Controller implements AdminController for Customer
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 * @see AdminController
 */
@Controller
@RequestMapping(value = "/customers")
public class CustomerController implements AdminController<Customer>{

	/**
	 * Injects an CustomerService implementation into customerService variable
	 * @see CustomerService
	 */
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * Injects an BirthdayFormat implementation into format variable to convert into a birthday
	 * @see BirthdayFormat
	 */
	@Autowired
	private BirthdayFormat format;

	/**
	 * List all Customer and put into view
	 */
	@Override
	public ModelAndView list(Pageable pageable, String msg) {
		Page<Customer> customersPage = customerService.listAll(pageable);
        PageWrapper<Customer> page = new PageWrapper<Customer>(customersPage, "/customers");
		
		ModelAndView mv= new ModelAndView("customers");
		mv.addObject("customers", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * Persist Customer
	 */
	@Override
	public ModelAndView save(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
		ModelAndView mv= new ModelAndView("redirect:/customers");
		if (result.hasErrors()){
			return add(customer);
    	}
		try {
			User user = userService.getUserByEmail(customer.getUser().getEmail());
			customer.setUser(null);
			customer.setBirthday(format.convertRegistrationBirthday(customer.getYear(), customer.getMonth(), customer.getDay()));
			user.setCustomer(customer);
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
            	result.addError(new ObjectError("msg", "E-mail already exists"));
            	return add(customer);
            }
		}
		mv.addObject("msg", "Customer saved successfully");
		return mv;
	}

	/**
	 * Call the add form to create new Customer
	 */
	@Override
	public ModelAndView add(Customer customer) {
		if(customer == null){
			customer = new Customer();			
		}
		ModelAndView mv = new ModelAndView("customersForm");
		mv.addObject("title", "Add New Customer");
		mv.addObject("roles", Arrays.asList(Role.values()));
    	mv.addObject("genders", Arrays.asList(Genders.values()));
    	mv.addObject("months", Arrays.asList(Months.values()));
    	mv.addObject("days", Arrays.asList(Days.values()));
    	mv.addObject("years", Arrays.asList(Years.values()));
    	mv.addObject("countries", Arrays.asList(Countries.values()));
		mv.addObject("customer", customer);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
		if(usersEmail.isEmpty()){
			String msg = "There is no email avaliable to link, Please insert users before create a Customer";
			return new ModelAndView("redirect:/customers?msg=" + msg);
		}
		return mv;
	}

	/**
	 * Call the edit form to edit Customer by its id
	 */
	@Override
	public ModelAndView edit(@PathVariable Integer id) {
		Customer customer = customerService.getById(id);
		ModelAndView mv = new ModelAndView("customersForm");
		mv.addObject("title", "Add New Customer");
		mv.addObject("roles", Arrays.asList(Role.values()));
    	mv.addObject("genders", Arrays.asList(Genders.values()));
    	mv.addObject("months", Arrays.asList(Months.values()));
    	mv.addObject("days", Arrays.asList(Days.values()));
    	mv.addObject("years", Arrays.asList(Years.values()));
    	mv.addObject("countries", Arrays.asList(Countries.values()));
		mv.addObject("customer", customer);
		return mv;
	}

	/**
	 * Delete a Customer by its id
	 */
	@Override
	public ModelAndView delete(@PathVariable Integer id) {
		customerService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/customers");
		mv.addObject("msg", "Customer Deleted");
		return mv;
	}
}