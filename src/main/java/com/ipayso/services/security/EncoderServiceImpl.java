package com.ipayso.services.security;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * @see BCryptPasswordEncoder
	 */
	@Autowired
	private BCryptPasswordEncoder encoderService;
	
    /**
	 * @see EncoderService encodeString method
	 */
	@Override
	public String encodeString(String input) {
		return encoderService.encode(input);
	}
	
    /**
	 * @see EncoderService checkEncode method
	 */
	@Override
	public boolean checkEncode(String rawPassword, String encodedPassword) {
		return encoderService.matches(rawPassword, encodedPassword);
	}
}
