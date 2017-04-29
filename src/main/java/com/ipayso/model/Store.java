package com.ipayso.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Ticket.class -> This class is an model for tickets
 * @author Cleber Oliveira
 * @version 1.0
 * @see AbstractModelClass
 * @see @MappedSuperclass
 * @see @Entity
 */
@Entity
public class Store extends AbstractModelClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ticket ticket;
	
	@OneToOne (targetEntity = Account.class, fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
	@JoinColumn(name = "account_id", referencedColumnName = "id", updatable = true, insertable = true)
	private Account account;

	
	public Store(Ticket ticket, Account account) {
		super();
		this.ticket = ticket;
		this.account = account;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}