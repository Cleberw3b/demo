package com.ipayso.util.enums;

/**
 * Week.class -> Enum for Week
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Week {
	SUNDAY("Sunday"),
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday");
	
	private String description;
	
	Week (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
