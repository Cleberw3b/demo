package com.ipayso.repositories;

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
	
}