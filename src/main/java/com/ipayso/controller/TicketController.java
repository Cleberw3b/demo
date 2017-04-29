package com.ipayso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ipayso.model.Customer;
import com.ipayso.model.Ticket;
import com.ipayso.model.User;
import com.ipayso.services.CustomerService;
import com.ipayso.services.UserService;

/**
 * TicketController.class -> 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 */
@Controller
public class TicketController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/buyTicket", method = RequestMethod.GET)
	public ModelAndView getContent(){
		ModelAndView mv = new ModelAndView("/buyTicket");
		return mv;
	}
	
	@RequestMapping( method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
    public ResponseEntity<Ticket> buy(@RequestBody Ticket input, Authentication auth){
		User user = userService.getUserByEmail(auth.getName());
		Customer customer = user.getCustomer();
		customer.getAccount().setValue(input.getValue());
		try {
			customerService.saveOrUpdate(customer);	
		} catch (Exception e) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND) ;
		}
		return new ResponseEntity<Ticket>(customer.getTicket(), HttpStatus.OK) ;
    }
}