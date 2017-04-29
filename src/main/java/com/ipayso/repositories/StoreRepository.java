package com.ipayso.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.Store;

/**
 * TicketRepository.class -> This interface is a repository for Ticket,  any TicketRepository
 * 							   instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see Store
 */
public interface StoreRepository extends CrudRepository<Store, Integer>{
	
	/**
	 * Find all Ticket and make it pageable
	 * @param pageable
	 * @return Page<Store>
	 * @see Pageable
	 */
	Page<Store> findAll(Pageable pageable);

	/**
	 * List all Ticket
	 * @return List<Store>
	 */
	List<Store> findAll();

}