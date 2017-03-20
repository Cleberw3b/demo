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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.Customer;
import com.ipayso.model.User;
import com.ipayso.services.CustomerService;
import com.ipayso.services.UserService;
import com.ipayso.util.enums.Countries;
import com.ipayso.util.enums.Days;
import com.ipayso.util.enums.Genders;
import com.ipayso.util.enums.Months;
import com.ipayso.util.enums.Role;
import com.ipayso.util.enums.Years;
import com.ipayso.util.formaters.BirthdayFormat;

@Controller
public class CustomerController {

	/**
	 * TODO
	 */
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private BirthdayFormat format;
	
	@RequestMapping(value = "/customers",  method = RequestMethod.GET)
	public ModelAndView listcustomers(Pageable pageable, @RequestParam(required = false, value = "msg") String msg){
		
		Page<Customer> customersPage = customerService.listAll(pageable);
        PageWrapper<Customer> page = new PageWrapper<Customer>(customersPage, "/customers");
		
		ModelAndView mv= new ModelAndView("customers");
		mv.addObject("customers", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		return mv;
	}
	
	@RequestMapping(value = "/customers/save", method = RequestMethod.POST)
	public ModelAndView saveCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes){		
		ModelAndView mv= new ModelAndView("redirect:/customers");
		if (result.hasErrors()){
			return addCustomer(customer);
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
            	return addCustomer(customer);
            }
		}
		mv.addObject("msg", "Customer added successfully");
		return mv;
	}

	@RequestMapping("customers/add")
	public ModelAndView addCustomer(Customer customerParam){
		if(customerParam == null){
			customerParam = new Customer();			
		}
		ModelAndView mv = new ModelAndView("customersForm");
		mv.addObject("title", "Add New Customer");
		mv.addObject("roles", Arrays.asList(Role.values()));
    	mv.addObject("genders", Arrays.asList(Genders.values()));
    	mv.addObject("months", Arrays.asList(Months.values()));
    	mv.addObject("days", Arrays.asList(Days.values()));
    	mv.addObject("years", Arrays.asList(Years.values()));
    	mv.addObject("countries", Arrays.asList(Countries.values()));
		mv.addObject("customer", customerParam);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
		if(usersEmail.isEmpty()){
			String msg = "There is no email avaliable to link, Please insert users before create a Customer";
			return new ModelAndView("redirect:/customers?msg=" + msg);
		}
		return mv;
	}

	@RequestMapping("customers/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Integer id){
		Customer customer = customerService.getById(id);ModelAndView mv = new ModelAndView("customersForm");
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

	@RequestMapping("customers/delete/{id}")
	public ModelAndView deleteCustomer(@PathVariable Integer id){
		customerService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/customers");
		mv.addObject("msg", "Customer Deleted");
		return mv;
	}
}