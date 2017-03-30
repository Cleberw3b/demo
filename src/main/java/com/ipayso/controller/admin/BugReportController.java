package com.ipayso.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.BugReportTicket;
import com.ipayso.model.User;
import com.ipayso.services.BugReportTicketService;
import com.ipayso.services.UserService;

@Controller
public class BugReportController {
	
	
	/**
	 * Injects an BugReportTicketService implementation into bugReportTicketService variable
	 * @param bugReportTicketService
	 * @see BugReportTicketService
	 */
	@Autowired
	private BugReportTicketService bugReportTicketService;

	@Autowired
	private UserService userService;
	
	@Autowired
    private ServletContext servletContext;
	
	@RequestMapping(value = "/bugReport",  method = RequestMethod.GET)
	public ModelAndView newBugReportTicket(BugReportTicket bugReportTicket){
		ModelAndView mv= new ModelAndView("bugReport");
		return mv;
	}
	
	/**
	 * TODO
	 */
	@RequestMapping(value = "/bugs",  method = RequestMethod.GET)
	public ModelAndView newBugReportTicket(Pageable pageable, @RequestParam(required = false, value = "msg") String msg){
		Page<BugReportTicket> bugsPage = bugReportTicketService.listAll(pageable);
        PageWrapper<BugReportTicket> page = new PageWrapper<BugReportTicket>(bugsPage, "/bugs");
		
		ModelAndView mv= new ModelAndView("bugs");
		mv.addObject("bugs", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		servletContext.getContextPath();
		//mv.addObject("request", request);
		return mv;
	}
	
	@RequestMapping("/bugs/add")
	public ModelAndView addBug(BugReportTicket bugTicket){
		if(bugTicket == null){
			bugTicket = new BugReportTicket();			
		}
		
		ModelAndView mv = new ModelAndView("bugsForm");
		mv.addObject("title", "Add New Bug Ticket");
		mv.addObject("bug", bugTicket);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
    	return mv;
	}
	
	@RequestMapping("/bugs/edit/{id}")
	public ModelAndView editBug(@PathVariable Integer id){
		BugReportTicket bug = bugReportTicketService.getById(id);
		ModelAndView mv = new ModelAndView("bugsForm");
		mv.addObject("title", "Add New Bug Ticket");
		mv.addObject("bug", bug);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
    	return mv;
	}
	
	
	@RequestMapping(value = "/bugs/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid BugReportTicket bug, BindingResult result, RedirectAttributes attributes){		
		if (result.hasErrors()){
			return addBug(bug);
    	}
		if(bug.isDone()){
			bug.setClosedDate(new Date());
		}
		User user = userService.getUserByEmail(bug.getUser().getEmail());
		bug.setUser(user);		
		if(bug.getUser() == null){
			user = new User();
			user.setEmail("");
			bug.setUser(user);
		}
		bugReportTicketService.saveOrUpdate(bug);
		ModelAndView mv= new ModelAndView("redirect:/bugs");
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
		bugReportTicket =  bugReportTicketService.saveNewBug(bugReportTicket);
    	return mv;
    }
}
