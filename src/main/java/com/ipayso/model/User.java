package com.ipayso.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "userLogin")
public class User extends AbstractModelClass{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "email",  unique = true)
	@ValidEmail
	@NotBlank (message = "E-mail Required")
	String email;
	
	@NotBlank (message = "Password Required")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@NotBlank (message = "Day Required")
	private String day;
	
	@NotBlank (message = "Month Required")
	private String month;	
	
	@NotBlank (message = "Year Required")
	private String year;

	@NotBlank (message = "Gender Required")
	private String gender;
	
	private String role;
	
	@NotBlank (message = "Country Required")
	private String country;
	
	@NotBlank (message = "Bank Required")
	private String bank;
	
	@NotBlank (message = "Bank Account Required")
	private String bank_acc;
	
	private String question1;
	
	private String question2;
	
	private Boolean enabled = false;

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
}