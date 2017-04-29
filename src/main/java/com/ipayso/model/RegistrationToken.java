package com.ipayso.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * RegistrationToken.class -> This class is an model for token that be issued on registration
 * @author Cleber Oliveira
 * @version 1.0
 * @see AbstractModelClass
 * @see @MappedSuperclass
 * @see @Entity
 */
@Entity
public class RegistrationToken extends AbstractModelClass{

	/**
	 * Here is the variables and their validator
	 */
	private static final long serialVersionUID = 1L;

	private static final int EXPIRATION = 60 * 24;

	private String token;

	/**
	 * Reference a user as foreign key
	 */
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	private Date expiryDate;
	
	private boolean used = false;

	public RegistrationToken() {
		super();
	}
	
	public RegistrationToken(String token) {
		super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public RegistrationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Calculate an expire date and return how long it will last
	 * @param expiryTimeInMinutes
	 * @return Date
	 */
	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	/**
	 * Updates an existing token with a new expiration date
	 * @param token
	 */
	public void updateToken(String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
}