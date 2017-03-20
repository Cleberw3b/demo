package com.ipayso.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.ipayso.util.enums.Role;

@Entity
public class Customer extends AbstractModelClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	@NotBlank (message = "Gender Required")
	private String gender;
	
	@NotBlank (message = "Country Required")
	private String country;

	@NotBlank (message = "Bank Required")
	private String bank;

	@NotBlank (message = "Bank Account Required")
	private String bank_acc;

	private String question1;

	private String question2;

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
	
}
