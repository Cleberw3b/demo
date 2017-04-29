package com.ipayso.controller.admin;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.BugReport;
import com.ipayso.model.User;
import com.ipayso.services.BugReportService;
import com.ipayso.services.UserService;
import com.ipayso.util.PageWrapper;

/**
 * BugController.class -> This Controller implements AdminController for BugReportTicket
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 * @see AdminController
 */
@Controller
@RequestMapping(value = "/bugs")
public class BugController implements AdminController<BugReport>{
	
	/**
	 * Injects an BugReportTicketService implementation into bugReportTicketService variable
	 * @see BugReportService
	 */
	@Autowired
	private BugReportService bugReportTicketService;

	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

    /**
     * Injects MessageSource to capture messages from message.properties
     */
	@Autowired
	private MessageSource messages;
	
	/**
	 * List all BugReportTicket and put into view
	 */
	@Override
	public ModelAndView list(Pageable pageable, String msg) {
		Page<BugReport> bugsPage = bugReportTicketService.listAll(pageable);
        PageWrapper<BugReport> page = new PageWrapper<BugReport>(bugsPage, "/bugs");
		
		ModelAndView mv= new ModelAndView("bugs");
		mv.addObject("bugs", page.getContent());
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		return mv;
	}

	
	/**
	 * Persist BugReportTicket
	 */
	@Override
	public ModelAndView save(@Valid BugReport bugTicket, BindingResult result, RedirectAttributes attributes, WebRequest request) {
		ModelAndView mv= new ModelAndView("redirect:/bugs");
		if (result.hasErrors()){
			return add(bugTicket, request);
    	}
		if(bugTicket.isDone()){
			bugTicket.setClosedDate(new Date());
		}
		User user = userService.getUserByEmail(bugTicket.getUser().getEmail());
		bugTicket.setUser(user);		
		if(bugTicket.getUser() == null){
			user = new User();
			user.setEmail("");
			bugTicket.setUser(user);
		}
		bugReportTicketService.saveOrUpdate(bugTicket);
		mv.addObject("msg", messages.getMessage("message.bug.saved.success", null, request.getLocale()));
		return mv;
	}

	/**
	 * Call the add form to create new BugReportTicket
	 */
	@Override
	public ModelAndView add(BugReport bugTicket, WebRequest request) {
		if(bugTicket == null){
			bugTicket = new BugReport();			
		}
		
		ModelAndView mv = new ModelAndView("bugsForm");
		mv.addObject("title", messages.getMessage("message.bug.title.add", null, request.getLocale()));
		mv.addObject("bug", bugTicket);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
    	return mv;
	}

	/**
	 * Call the edit form to edit BugReportTicket by its id
	 */
	@Override
	public ModelAndView edit(@PathVariable Integer id, WebRequest request) {
		BugReport bug = bugReportTicketService.getById(id);
		ModelAndView mv = new ModelAndView("redirect:/bugsForm");
		mv.addObject("title", messages.getMessage("message.bug.title.edit", null, request.getLocale()));
		mv.addObject("bug", bug);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
    	return mv;
	}

	/**
	 * Delete a BugReportTicket by id
	 */
	@Override
	public ModelAndView delete(@PathVariable Integer id, WebRequest request) {
		bugReportTicketService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/bugsForm");
		mv.addObject("msg", messages.getMessage("message.bug.deleted", null, request.getLocale()));
		return mv;
	}
}
