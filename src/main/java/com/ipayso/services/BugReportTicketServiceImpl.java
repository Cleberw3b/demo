package com.ipayso.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.BugReportTicket;
import com.ipayso.model.User;
import com.ipayso.repositories.BugReportTicketRepository;
import com.ipayso.services.security.SecurityService;

/**
 * BugReportTicketServiceImpl.class -> This Service offers a BugReportTicketService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see BugReportTicketService
 */
@Service
public class BugReportTicketServiceImpl implements BugReportTicketService{
	
	/**
	 * Injects BugReportTicketRepository to make transactions on database
	 * @see BugReportTicketRepository
	 */
	@Autowired
	private BugReportTicketRepository bugReportTicketRepository;
	
    /**
     * Injects an SecurityService implementation into securityService variable
     * @param securityService
     * @see SecurityService
     */
    @Autowired
    private SecurityService securityService;
	
    /**
     * Injects an UserService implementation into userService variable
     * @param userService
     * @see UserService
     */
	private UserService userService;
	
	/**
	 * List all BugReportTicket on database
     * @return List
     */
	@Override
	public List<BugReportTicket> listAll() {
		List<BugReportTicket> reports = new ArrayList<>();
        bugReportTicketRepository.findAll().forEach(reports::add);
        return reports;
	}
	
	/**
	 * Get an BugReportTicket by its ID
	 * @param id as Integer
     * @return BugReportTicket
     */
	@Override
	public BugReportTicket getById(Integer id) {
		return bugReportTicketRepository.findOne(id);
	}
	
	/**
	 * Save or Update an BugReportTicket and return it updated 
	 * @param BugReportTicket
     * @return BugReportTicket
     */
	@Override
	public BugReportTicket saveOrUpdate(BugReportTicket domainObject) {
		return bugReportTicketRepository.save(domainObject);
	}
	
	/**
	 * Delete an BugReportTicket on database
	 * @param id as Integer
     */
	@Override
	public void delete(Integer id) {
		bugReportTicketRepository.delete(id);
		
	}

	@Override
	public Page<BugReportTicket> listAll(Pageable pageable) {
		return bugReportTicketRepository.findAll(pageable);
	}

	@Override
	public BugReportTicket saveNewBug(BugReportTicket bug) {
		User user= userService.getUserByEmail(securityService.findLoggedInUsername());
		if (user == null){
			user= userService.getUserByEmail("admin@ipayso.com");
		}
		bug.setUser(user);
		bug.setDone(false);
		return saveOrUpdate(bug); 
	}
}
