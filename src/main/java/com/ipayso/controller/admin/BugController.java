package com.ipayso.controller.admin;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipayso.model.BugReportTicket;
import com.ipayso.model.User;
import com.ipayso.model.util.PageWrapper;
import com.ipayso.services.BugReportTicketService;
import com.ipayso.services.UserService;

/**
 * BugController.class -> This Controller implements AdminController for BugReportTicket
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Controller
 * @see AdminController
 */
@Controller
@RequestMapping(value = "/bugs")
public class BugController implements AdminController<BugReportTicket>{
	
	/**
	 * Injects an BugReportTicketService implementation into bugReportTicketService variable
	 * @see BugReportTicketService
	 */
	@Autowired
	private BugReportTicketService bugReportTicketService;

	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;

	/**
	 * List all BugReportTicket and put into view
	 */
	@Override
	public ModelAndView list(Pageable pageable, String msg) {
		Page<BugReportTicket> bugsPage = bugReportTicketService.listAll(pageable);
        PageWrapper<BugReportTicket> page = new PageWrapper<BugReportTicket>(bugsPage, "/bugs");
		
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
	public ModelAndView save(@Valid BugReportTicket bugTicket, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){
			return add(bugTicket);
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
		ModelAndView mv= new ModelAndView("redirect:/bugs");
		return mv;
	}

	/**
	 * Call the add form to create new BugReportTicket
	 */
	@Override
	public ModelAndView add(BugReportTicket bugTicket) {
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

	/**
	 * Call the edit form to edit BugReportTicket by its id
	 */
	@Override
	public ModelAndView edit(@PathVariable Integer id) {
		BugReportTicket bug = bugReportTicketService.getById(id);
		ModelAndView mv = new ModelAndView("redirect:/bugsForm");
		mv.addObject("title", "Add New Bug Ticket");
		mv.addObject("bug", bug);
		List<String> usersEmail = userService.listAllHasNoCustomer();			
		mv.addObject("users", usersEmail);
    	return mv;
	}

	/**
	 * Delete a BugReportTicket by id
	 */
	@Override
	public ModelAndView delete(@PathVariable Integer id) {
		bugReportTicketService.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/bugsForm");
		mv.addObject("msg", "Bug Deleted");
		return mv;
	}
}
