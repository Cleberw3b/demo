package com.ipayso.model;

import org.hibernate.validator.constraints.NotBlank;

import com.ipayso.util.validators.ValidEmail;

public class UserRegister {

	@ValidEmail
	@NotBlank (message = "E-mail Required")
	String email;

	@NotBlank (message = "Password Required")
	private String password;

	private String passwordConfirm;
	
	@NotBlank (message = "Insert Day of Birthday")
	private String day;
	
	@NotBlank (message = "Insert Month of Birthday")
	private String month;
	
	@NotBlank (message = "Insert Year of Birthday")
	private String year;
	
	@NotBlank (message = "Gender Required")
	private String gender;
	
	@NotBlank (message = "Country Required")
	private String country;

	@NotBlank (message = "Bank Required")
	private String bank;

	@NotBlank (message = "Bank Account Required")
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
