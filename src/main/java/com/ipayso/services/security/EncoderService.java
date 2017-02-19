package com.ipayso.services.security;

/**
 * SecurityService.class -> This interface assign all the methods an EncoderService should implement
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface EncoderService {
	
	String encodeString(String input);
	
	boolean checkEncode(String rawPassword, String encodedPassword);

}
