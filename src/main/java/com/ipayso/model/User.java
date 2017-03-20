package com.ipayso.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.ipayso.util.validators.ValidEmail;

/**
 * User.class -> This class is the User model which has all the User Information for business purpose,
 * 				 it extends AbstractModelClass to inherit its attributes and methods. All attributes are
 * 				 columns on database but passwordConfirm annotated as @Transient which make the DB don't
 * 				 see this attribute.
 * @author Cleber Oliveira
 * @version 1.0
 * @see AbstractModelClass
 * @see @Entity
 * @see @Table
 */
@Entity
@Table(name = "user_login")
public class User extends AbstractModelClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	@ValidEmail
	@NotBlank (message = "E-mail Required")
	String email;

	@NotBlank (message = "Password Required")
	private String password;
	
	@OneToOne (targetEntity = Customer.class, fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
	@JoinColumn(name = "customer_id", referencedColumnName = "id", updatable = true, insertable = true)
	private Customer customer;
	
	@NotBlank (message = "Role Required")
	private String role;

	private boolean enabled;

	private boolean accountExpired;

	private boolean accountLocked;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
}