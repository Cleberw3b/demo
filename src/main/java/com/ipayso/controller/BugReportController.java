package com.ipayso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.BugReportTicket;
import com.ipayso.services.BugReportTicketService;

@Controller
public class BugReportController {
	

	/**
	 * Injects an BugReportTicketService implementation into bugReportTicketService variable
	 * @param bugReportTicketService
	 * @see BugReportTicketService
	 */
	@Autowired
	private BugReportTicketService bugReportTicketService;
	
	@RequestMapping(value = "/bugReport",  method = RequestMethod.GET)
	public ModelAndView newBugReportTicket(BugReportTicket bugReportTicket){
		ModelAndView mv= new ModelAndView("bugReport");
		return mv;
	}

	/**
	 * Map requests from /bugReport form to open a ticket
	 * @param report
	 * @param result
	 * @param attributes
	 * @return successful reported
	 * @see BugReportTicket
	 * @see @RequestMapping
	 */
	@RequestMapping(value = "/bugReport",  method = RequestMethod.POST)
    public ModelAndView bugReport(@Valid BugReportTicket bugReportTicket, BindingResult result, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("home");
		if(result.hasErrors()){
			return newBugReportTicket(bugReportTicket);
		}
		bugReportTicket =  bugReportTicketService.saveOrUpdate(bugReportTicket);
    	return mv;
    }
}
