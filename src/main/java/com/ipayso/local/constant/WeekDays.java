package com.ipayso.local.constant;

public enum WeekDays {
	SUNDAY("Sunday"),
	MONDAY("Monday"),
	TUESDAY("Tuesday"),
	WEDNESDAY("Wednesday"),
	THURSDAY("Thursday"),
	FRIDAY("Friday"),
	SATURDAY("Saturday");
	
	private String description;
	
	WeekDays (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
