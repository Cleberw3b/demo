package com.ipayso.model;

import org.hibernate.validator.constraints.NotBlank;

import com.ipayso.util.validators.ValidEmail;

/**
 * User.class -> This class is an model for registration, lately it will split into User and Customer
 * @author Cleber Oliveira
 * @version 1.0
 */
public class UserRegister {

	/**
	 * Here is the variables and their validator
	 * @see @ValidEmail
	 */
	@ValidEmail
	@NotBlank (message = "{NotEmpty.user.email}")
	String email;

	@NotBlank (message = "{NotEmpty.user.password}")
	private String password;

	private String passwordConfirm;
	
	@NotBlank (message = "{NotEmpty.user.day}")
	private String day;
	
	@NotBlank (message = "{NotEmpty.user.month}")
	private String month;
	
	@NotBlank (message = "{NotEmpty.user.year}")
	private String year;
	
	@NotBlank (message = "{NotEmpty.user.gender}")
	private String gender;
	
	@NotBlank (message = "{NotEmpty.user.country}")
	private String country;

	@NotBlank (message = "{NotEmpty.user.bank}")
	private String bank;

	@NotBlank (message = "{NotEmpty.user.bank.acc}")
	private String bank_acc;

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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	
}
