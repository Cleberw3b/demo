package com.ipayso.services.security;

/**
 * EncoderService.class -> This interface assign all the methods an EncoderService should implement
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface EncoderService {
	
	/**
	 * This method encodes an String
	 * @param input
	 * @return
	 */
	String encodeString(String input);
	
	/**
	 * Verify if the encodedPassword is the rawPassword
	 * @param rawPassword
	 * @param encodedPassword
	 * @return true when matches 
	 */
	boolean checkEncode(String rawPassword, String encodedPassword);

}

