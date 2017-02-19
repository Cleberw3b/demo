package com.ipayso.util.enums;

/**
 * Genders.class -> Enum for Genders
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Genders {
	MALE ("Male"), 
	FAMALE ("Female");
	
	/**
	 * Create a description to get the enum's description
	 */
	private String description;
	
	Genders (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
} 
