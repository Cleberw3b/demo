package com.ipayso.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * EncoderServiceImpl.class -> This Service offers a EncoderService implementation to encode and check whether
 * 							   a password matchers its encoded form.
 * @author Cleber Oliveira
 * @version 1.0
 * @see EncoderService
 */
@Service
public class EncoderServiceImpl implements EncoderService {

	
	/**
	 * Injects BCryptPasswordEncoder to use encryption methods to check and encode strings
	 * @see AuthenticationManager
	 */
	@Autowired
	private BCryptPasswordEncoder encoderService;
	
    /**
	 * This method encodes a String 
	 * @return the encoded string
	 */
	@Override
	public String encodeString(String input) {
		return encoderService.encode(input);
	}
	
	/**
	 * This method check matchers between an encode string and a raw string
	 * @return true when they match and false when they don't
	 */
	@Override
	public boolean checkEncode(String rawPassword, String encodedPassword) {
		return encoderService.matches(rawPassword, encodedPassword);
	}
}
