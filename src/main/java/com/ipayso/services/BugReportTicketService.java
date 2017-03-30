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
	
	Page<BugReportTicket> listAll(Pageable pageable);
	
	BugReportTicket saveNewBug(BugReportTicket bug);

}
