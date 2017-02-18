package com.ipayso.util.enums;

public enum Genders {
	MALE ("Male"), 
	FAMALE ("Female");
	
	
	private String description;
	
	Genders (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
} 