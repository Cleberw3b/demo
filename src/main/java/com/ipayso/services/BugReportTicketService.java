package com.ipayso.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.BugReportTicket;

/**
 * BugReportTicketService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see BugReportTicket
 */
public interface BugReportTicketService extends CRUDService<BugReportTicket>{
	
	/**
	 * Find all BugReportTicket and make it pageable
	 * @param pageable
	 * @return Page<BugReportTicket>
	 * @see Pageable
	 */
	Page<BugReportTicket> listAll(Pageable pageable);
	
	/**
	 * Persist BugReportTicket instance
	 * @param bug
	 * @return BugReportTicket
	 */
	BugReportTicket saveNewBug(BugReportTicket bug);
}
