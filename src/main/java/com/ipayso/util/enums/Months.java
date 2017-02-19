package com.ipayso.util.enums;

/**
 * Months.class -> Enum for Months
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Months {
	
	JANUARY("January"),
	FEBRUARY("February"),
	MARCH("March"),
	APRIL("April"),
	MAY("May"),
	JUNE("June"),
	JULY("July"),
	AUGUST("August"),
	SEPTEMBER("September"),
	OCTOBER("October"),
	NOVEMBER("November"),
	DECEMBER("December");
	
	/**
	 * Create a description to get the enum's description
	 */
	private String description;

	Months (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
