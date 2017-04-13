package com.ipayso.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.BugReportTicket;
import com.ipayso.services.BugReportTicketService;

/**
 * BugController.class -> This Controller handle requests for bug Report
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 * @see AdminController
 */
@Controller
public class BugReportController{
	
	
	/**
	 * Injects an BugReportTicketService implementation into bugReportTicketService variable
	 * @param bugReportTicketService
	 * @see BugReportTicketService
	 */
	@Autowired
	private BugReportTicketService bugReportTicketService;
	
	/**
	 * This method get the path from view that sent a request, create new ticket and add it to view.
	 * @param bugReportTicket
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/bugReport", method = RequestMethod.GET)
	public ModelAndView newBugReportTicket(BugReportTicket bugReportTicket, @RequestParam(required = true, value = "path") String path){
		ModelAndView mv= new ModelAndView("/bugReport");
		if(bugReportTicket == null){
			bugReportTicket = new BugReportTicket();
		}
		bugReportTicket.setUrl(path);
		mv.addObject("bugReportTicket", bugReportTicket);
		return mv;
	}

	/**
	 * Map requests from /bugReport form and open a ticket containing a bug
	 * @param report
	 * @param result
	 * @param attributes
	 * @return successful reported
	 * @see BugReportTicket
	 * @see @RequestMapping
	 */
	@RequestMapping(value = "/bugReport",  method = RequestMethod.POST)
    public ModelAndView bugReport(@Valid BugReportTicket bugReportTicket, BindingResult result, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/home");
		if(result.hasErrors()){
			return newBugReportTicket(bugReportTicket, bugReportTicket.getUrl());
		}
		bugReportTicket =  bugReportTicketService.saveNewBug(bugReportTicket);
    	return mv;
    }

	
}
