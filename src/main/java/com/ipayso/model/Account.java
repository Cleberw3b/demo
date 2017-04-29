package com.ipayso.model;

import javax.persistence.Entity;
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
public class Account extends AbstractModelClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long value;
	
	private int pin;
	
	@OneToOne (mappedBy="account")
	private Customer customer;

	@OneToOne (mappedBy="account")
	private Store store;

	public Account(long value, int pin) {
		super();
        this.value = value;
        this.pin = pin;
    }
	
	public Account(){
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}	
}