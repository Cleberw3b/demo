package com.ipayso.services.security;

public interface EncoderService {
	
	String encodeString(String input);
	boolean checkEncode(String rawPassword, String encodedPassword);

}
