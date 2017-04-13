package com.ipayso.util.enums;

/**
 * TokenStatus.class -> Enum for TokenStatus
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum TokenStatus {
	
	TOKEN_INVALID("invalidToken"),
	TOKEN_EXPIRED("expired"),
	TOKEN_VALID("valid");
	
	/**
	 * Create a description to get the enum's description
	 */
	private String description;
	
	TokenStatus (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
