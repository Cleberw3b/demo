package com.ipayso.services;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;

/**
 * VerificationTokenService.class -> This interface extends all methods from CRUDService
 * @author Cleber Oliveira
 * @version 1.0
 * @see CRUDService
 * @see RegistrationToken
 */
public interface RegistrationTokenService extends CRUDService<RegistrationToken>{

	String validateVerificationToken(String token);

	RegistrationToken createToken(User user, String token);

	RegistrationToken getByToken(String token);

	RegistrationToken generateNewVerificationToken(String token);
}
