package com.ipayso.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ipayso.model.Store;

/**
 * BugReportTicketService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see Store
 */
public interface StoreService extends CRUDService<Store>{
	
	/**
	 * Find all Ticket and make it pageable
	 * @param pageable
	 * @return Page<Store>
	 * @see Pageable
	 */
	Page<Store> listAll(Pageable pageable);
}
