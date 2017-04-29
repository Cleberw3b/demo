package com.ipayso.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.ipayso.util.enums.Role;

/**
 * Customer.class -> This class is an model for customers
 * @author Cleber Oliveira
 * @version 1.0
 * @see AbstractModelClass
 * @see @MappedSuperclass
 * @see @Entity
 */
@Entity
public class Customer extends AbstractModelClass{

	/**
	 * Here is the variables and their validator
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This creates a column in user for customer as foreign key 
	 */
	@OneToOne (mappedBy="customer")
	private User user;
	
	private String username;
	
	private String birthday;

	private String role = Role.BUYER.getDescription();
	
	@Transient
	private String day;
	
	@Transient
	private String month;
	
	@Transient
	private String year;
	
	@NotBlank (message = "{NotEmpty.user.gender}")
	private String gender;
	
	@NotBlank (message = "{NotEmpty.user.country}")
	private String country;

	@NotBlank (message = "{NotEmpty.user.bank}")
	private String bank;

	@NotBlank (message = "{NotEmpty.user.bank.acc}")
	private String bank_acc;

	private String question1;

	private String question2;
	
	@OneToOne (targetEntity = Account.class, fetch = FetchType.EAGER , cascade = {CascadeType.ALL})
	@JoinColumn(name = "account_id", referencedColumnName = "id", updatable = true, insertable = true)
	private Account account;
	
	@OneToOne (mappedBy="customer")
	private Ticket ticket;
	
	public Customer(User user, String username, String birthday, String role, String day, String month, String year,
			String gender, String country, String bank, String bank_acc, String question1, String question2,
			Account account, Ticket ticket) {
		super();
		this.user = user;
		this.username = username;
		this.birthday = birthday;
		this.role = role;
		this.day = day;
		this.month = month;
		this.year = year;
		this.gender = gender;
		this.country = country;
		this.bank = bank;
		this.bank_acc = bank_acc;
		this.question1 = question1;
		this.question2 = question2;
		this.account = account;
		this.ticket = ticket;
	}

	public Customer(){
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank_acc() {
		return bank_acc;
	}

	public void setBank_acc(String bank_acc) {
		this.bank_acc = bank_acc;
	}

	public String getQuestion1() {
		return question1;
	}
	
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
