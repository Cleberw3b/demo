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
     * @see SecurityService
     */
    @Autowired
    private SecurityService securityService;
	
    /**
     * Injects an UserService implementation into userService variable
     * @see UserService
     */
	private UserService userService;
	
	/**
	 * @see CRUDService method listAll
     */
	@Override
	public List<BugReportTicket> listAll() {
		List<BugReportTicket> reports = new ArrayList<>();
        bugReportTicketRepository.findAll().forEach(reports::add);
        return reports;
	}
	
	/**
	 * @see CRUDService method getById
     */
	@Override
	public BugReportTicket getById(Integer id) {
		return bugReportTicketRepository.findOne(id);
	}
	
	/**
	 * @see CRUDService method saveOrUpdate
     */
	@Override
	public BugReportTicket saveOrUpdate(BugReportTicket domainObject) {
		return bugReportTicketRepository.save(domainObject);
	}
	
	/**
	 * @see CRUDService method delete
     */
	@Override
	public void delete(Integer id) {
		bugReportTicketRepository.delete(id);
		
	}

	/**
	 * @see BugReportTicketRepository method listAll
	 */
	@Override
	public Page<BugReportTicket> listAll(Pageable pageable) {
		return bugReportTicketRepository.findAll(pageable);
	}
	
	/**
	 * @see BugReportTicketService method saveNewBug
	 */
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
