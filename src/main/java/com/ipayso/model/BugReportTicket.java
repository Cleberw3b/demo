package com.ipayso.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

/**
 * BugReportTicket.class -> This class is an model for bug tickets
 * @author Cleber Oliveira
 * @version 1.0
 * @see AbstractModelClass
 * @see @MappedSuperclass
 * @see @Entity
 */
@Entity
public class BugReportTicket extends AbstractModelClass{

	/**
	 * Here is the variables and their validator
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank (message = "Please inform a ticket title")
	private String title;
	
	@NotBlank (message = "Which page there is a bug?")
	private String url;
	
	@NotBlank (message = "Please describe behavior in order to fix this bug")
	private String description;
	
	/**
	 * This create a column in BugReport to link the user who made the report as foreign key
	 */
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	private boolean done;
	
	private Date closedDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
}
