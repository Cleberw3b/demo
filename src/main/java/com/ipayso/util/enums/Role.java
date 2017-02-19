package com.ipayso.util.enums;

/**
 * Role.class -> Enum for Roles
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Role {
	USER("User"),
	ADMIN("Admin");
	
	/**
	 * Create a description to get the enum's description
	 */
	private String description;
	
	Role (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
