package com.ipayso.local.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "userLogin")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id",  updatable = false)
	private Integer id;
	
	@Version
    private Integer version;
	
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
	
	
	@Column(name = "user_country",  length = 30)
	@NotBlank (message = "Country Required")
	private String country;
	
	@Column(name = "user_bank",  length = 30)
	@NotBlank (message = "Bank Required")
	private String bank;
	
	@Column(name = "user_bank_acc", length = 30)
	@NotBlank (message = "Bank Account Required")
	private String bank_acc;
	
	/*@OneToOne(mappedBy="userLogin", cascade = CascadeType.ALL)
	private Customer customer;*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	protected void setVersion(Integer version) {
		this.version = version;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", version=" + version + ", email=" + email + ", password=" + password + ", day="
				+ day + ", month=" + month + ", year=" + year + ", gender=" + gender + ", country=" + country
				+ ", bank=" + bank + ", bank_acc=" + bank_acc + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((bank_acc == null) ? 0 : bank_acc.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (bank_acc == null) {
			if (other.bank_acc != null)
				return false;
		} else if (!bank_acc.equals(other.bank_acc))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
