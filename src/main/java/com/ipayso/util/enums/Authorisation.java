package com.ipayso.util.enums;

/**
 * Authorisation.class -> Enum for Authorisation
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Authorisation {
	USER("User"),
	ADMIN("Admin");
	
	/**
	 * Create a description to get the enum's description
	 */
	private String description;
	
	Authorisation (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
