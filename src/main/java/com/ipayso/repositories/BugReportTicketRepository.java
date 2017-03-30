package com.ipayso.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipayso.model.BugReportTicket;
/**
 * BugReportTicketRepository.class -> This interface is a repository for BugReportTicket,
 * 									  any BugReportTicket instance is allowed to use CrudRepository methods
 * @author Cleber Oliveira
 * @version 1.0
 * @see CrudRepository
 * @see BugReportTicket
 */
public interface BugReportTicketRepository extends CrudRepository<BugReportTicket, Integer>{
	
	/**
	 * TODO
	 * @param pageable
	 * @return
	 */
	Page<BugReportTicket> findAll(Pageable pageable);

	List<BugReportTicket> findAll();
}