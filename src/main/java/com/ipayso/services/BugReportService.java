package com.ipayso.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.BugReport;

/**
 * BugReportTicketService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see BugReport
 */
public interface BugReportService extends CRUDService<BugReport>{
	
	/**
	 * Find all BugReportTicket and make it pageable
	 * @param pageable
	 * @return Page<BugReportTicket>
	 * @see Pageable
	 */
	Page<BugReport> listAll(Pageable pageable);
	
	/**
	 * Persist BugReportTicket instance
	 * @param bug
	 * @return BugReportTicket
	 */
	BugReport saveNewBug(BugReport bug);
}
