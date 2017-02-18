package com.ipayso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "userLogin")
public class User extends AbstractModelClass{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "email",  unique = true)
	@Email
	@NotBlank (message = "E-mail Required")
	String email;
	
	@Column(name = "user_password")
	@NotBlank (message = "Password Required")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name = "user_day")
	@NotBlank (message = "Day Required")
	private String day;
	
	@Column(name = "user_month")
	@NotBlank (message = "Month Required")
	private String month;	
	
	@Column(name = "user_year",  length = 30)
	@NotBlank (message = "Year Required")
	private String year;

	@Column(name = "user_gender",  length = 30)
	@NotBlank (message = "Gender Required")
	private String gender;
	
	@Column(name = "user_role",  length = 30)
	private String role;
	
	@Column(name = "user_country",  length = 30)
	@NotBlank (message = "Country Required")
	private String country;
	
	@Column(name = "user_bank",  length = 30)
	@NotBlank (message = "Bank Required")
	private String bank;
	
	@Column(name = "user_bank_acc", length = 30)
	@NotBlank (message = "Bank Account Required")
	private String bank_acc;
	
	@Column(name = "user_enabled", length = 30)
	private Boolean enabled = true;
	
	/*@OneToOne(mappedBy="userLogin", cascade = CascadeType.ALL)
	private Customer customer;*/

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
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}