package com.ipayso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.Ticket;
import com.ipayso.repositories.TicketRepository;

/**
 * UserServiceImpl.class -> This Service offers a UserService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see UserService
 */
@Service
public class TicketServiceImpl implements TicketService{
	
	/**
	 * Injects TicketRepository to make transactions on database
	 * @see TicketRepository
	 */
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> listAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getById(Integer id) {
		return ticketRepository.findOne(id);
	}

	@Override
	public Ticket saveOrUpdate(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public void delete(Integer id) {
		ticketRepository.delete(id);
	}

	@Override
	public Page<Ticket> listAll(Pageable pageable) {
		return ticketRepository.findAll(pageable);
	}
}
