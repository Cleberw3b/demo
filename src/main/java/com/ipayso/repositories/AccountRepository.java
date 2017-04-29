package com.ipayso.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.Account;

/**
 * TicketRepository.class -> This interface is a repository for Ticket,  any TicketRepository
 * 							   instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see Account
 */
public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	/**
	 * Find all Ticket and make it pageable
	 * @param pageable
	 * @return Page<Account>
	 * @see Pageable
	 */
	Page<Account> findAll(Pageable pageable);

	/**
	 * List all Ticket
	 * @return List<Account>
	 */
	List<Account> findAll();

}