package com.ipayso.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.Account;

/**
 * BugReportTicketService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see Account
 */
public interface AccountService extends CRUDService<Account>{
	
	/**
	 * Find all Ticket and make it pageable
	 * @param pageable
	 * @return Page<Account>
	 * @see Pageable
	 */
	Page<Account> listAll(Pageable pageable);
}
