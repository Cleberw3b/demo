package com.ipayso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.Store;
import com.ipayso.repositories.StoreRepository;
import com.ipayso.repositories.TicketRepository;

/**
 * UserServiceImpl.class -> This Service offers a UserService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see UserService
 */
@Service
public class StoreServiceImpl implements StoreService{
	
	/**
	 * Injects TicketRepository to make transactions on database
	 * @see TicketRepository
	 */
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> listAll() {
		return storeRepository.findAll();
	}

	@Override
	public Store getById(Integer id) {
		return storeRepository.findOne(id);
	}

	@Override
	public Store saveOrUpdate(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public void delete(Integer id) {
		storeRepository.delete(id);
	}

	@Override
	public Page<Store> listAll(Pageable pageable) {
		return storeRepository.findAll(pageable);
	}
}
