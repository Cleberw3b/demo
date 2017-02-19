package com.ipayso.util.enums;

/**
 * Days.class -> Enum for Days
 * @author Cleber Oliveira
 * @version 1.0
 */
public enum Days {

	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	ELEVEN("11"),
	TWELVE("12"),
	THIRTEEN("13"),
	FOURTEEN("14"),
	FIFTEEN("15"),
	SIXTEEN("16"),
	SEVENTEEN("17"),
	EIGHTEEN("18"),
	NINETEEN("19"),
	TWENTY("20"),
	TWENTY_ONE("21"),
	TWENTY_TWO("22"),
	TWENTY_THREE("23"),
	TWENTY_FOUR("24"),
	TWENTY_FIVE("25"),
	TWENTY_SIX("26"),
	TWENTY_SEVEN("27"),
	TWENTY_EIGHT("28"),
	TWENTY_NINE("29"),
	THIRTY("30"),
	THIRTY_ONE("31");

	/**
	 * Create a description to get the enum's description
	 */
	private String description;

	Days (String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
