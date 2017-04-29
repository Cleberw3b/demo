package com.ipayso.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.BugReport;
import com.ipayso.model.User;
import com.ipayso.repositories.BugReportRepository;
import com.ipayso.services.security.SecurityService;

/**
 * BugReportTicketServiceImpl.class -> This Service offers a BugReportTicketService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see BugReportService
 */
@Service
public class BugReportServiceImpl implements BugReportService{
	
	/**
	 * Injects BugReportTicketRepository to make transactions on database
	 * @see BugReportRepository
	 */
	@Autowired
	private BugReportRepository bugReportTicketRepository;
	
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
	public List<BugReport> listAll() {
		List<BugReport> reports = new ArrayList<>();
        bugReportTicketRepository.findAll().forEach(reports::add);
        return reports;
	}
	
	/**
	 * @see CRUDService method getById
     */
	@Override
	public BugReport getById(Integer id) {
		return bugReportTicketRepository.findOne(id);
	}
	
	/**
	 * @see CRUDService method saveOrUpdate
     */
	@Override
	public BugReport saveOrUpdate(BugReport domainObject) {
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
	 * @see BugReportRepository method listAll
	 */
	@Override
	public Page<BugReport> listAll(Pageable pageable) {
		return bugReportTicketRepository.findAll(pageable);
	}
	
	/**
	 * @see BugReportService method saveNewBug
	 */
	@Override
	public BugReport saveNewBug(BugReport bug) {
		User user= userService.getUserByEmail(securityService.findLoggedInUsername());
		if (user == null){
			//TODO make sure this dont happen in production
			user= userService.getUserByEmail("admin@ipayso.com");
		}
		bug.setUser(user);
		bug.setDone(false);
		return saveOrUpdate(bug); 
	}
}
