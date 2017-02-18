package com.ipayso.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderServiceImpl implements EncoderService {

	private BCryptPasswordEncoder encoderService;

    @Autowired
    public void setEncryptionService(BCryptPasswordEncoder encryptionService) {
        this.encoderService = encryptionService;
    }
	
	@Override
	public String encodeString(String input) {
		return encoderService.encode(input);
	}

	@Override
	public boolean checkEncode(String rawPassword, String encodedPassword) {
		return encoderService.matches(rawPassword, encodedPassword);
	}
}
