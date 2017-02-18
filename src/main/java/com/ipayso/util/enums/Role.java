package com.ipayso.util.enums;

public enum Role {
	USER("User"),
	ADMIN("Admin");
	
	private String description;
	
	Role (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}