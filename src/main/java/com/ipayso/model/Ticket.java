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
public class Ticket extends AbstractModelClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long price;
	
	private Long value;
	
	@OneToOne (targetEntity = Customer.class, fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
	@JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = true, insertable = true)
	private Customer customer;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}