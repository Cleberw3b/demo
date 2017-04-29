package com.ipayso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipayso.model.Account;
import com.ipayso.repositories.AccountRepository;
import com.ipayso.repositories.TicketRepository;

/**
 * UserServiceImpl.class -> This Service offers a UserService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see Account
 */
@Service
public class AccountServiceImpl implements AccountService{
	
	/**
	 * Injects TicketRepository to make transactions on database
	 * @see TicketRepository
	 */
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> listAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account getById(Integer id) {
		return accountRepository.findOne(id);
	}

	@Override
	public Account saveOrUpdate(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(Integer id) {
		accountRepository.delete(id);
	}

	@Override
	public Page<Account> listAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}
}
