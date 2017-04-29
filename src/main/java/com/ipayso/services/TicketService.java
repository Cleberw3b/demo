package com.ipayso.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.BugReport;
import com.ipayso.model.Ticket;

/**
 * BugReportTicketService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see BugReport
 */
public interface TicketService extends CRUDService<Ticket>{
	
	/**
	 *  * Find all Ticket
	 * @return List<Ticket>
	 */
    List<Ticket> listAll();
	
	/**
	 * Find all Ticket and make it pageable
	 * @param pageable
	 * @return Page<BugReportTicket>
	 * @see Pageable
	 */
	Page<Ticket> listAll(Pageable pageable);
}
