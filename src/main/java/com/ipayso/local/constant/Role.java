package com.ipayso.local.constant;

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
