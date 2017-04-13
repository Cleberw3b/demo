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

	/**
	 * Create an VerificationToken and return it updated 
	 * @param RegistrationToken
     * @return RegistrationToken
     */
	RegistrationToken createToken(User user, String token);

	/**
	 * Get the token by its UUID string
	 * @param token
	 * @return RegistrationToken
	 */
	RegistrationToken getByToken(String token);

	/**
	 * Generate and Update an existing token
	 * @param token
	 * @return RegistrationToken
	 */
	RegistrationToken generateNewVerificationToken(String token);

	/**
	 * This method verify if the token is Valid, Invalid or Expired
	 * @param token
	 * @return String
	 */
	String validateVerificationToken(String token);


}
