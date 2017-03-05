package com.ipayso.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;
import com.ipayso.repositories.RegistrationTokenRepository;

/**
 * VerificationTokenServiceImpl.class -> This Service offers a VerificationTokenService implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see VerificationTokenService
 */
@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService{
	
	public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";
	
	/**
	 * Injects verificationTokenRepository to make transactions on database
	 * @see RegistrationTokenRepository
	 */
	@Autowired
	private RegistrationTokenRepository registrationTokenRepository;
	
	/**
	 * Injects an UserService implementation into userService variable
	 * @see UserService
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * List all RegistrationToken on database
     * @return List
     */
	@Override
	public List<?> listAll() {
		List<RegistrationToken> tokens = new ArrayList<>();
        registrationTokenRepository.findAll().forEach(tokens::add);
        return tokens;
	}
	
	/**
	 * Get an RegistrationToken by its ID
	 * @param id as Integer
     * @return RegistrationToken
     */
	@Override
	public RegistrationToken getById(Integer id) {
		return registrationTokenRepository.findOne(id);
	}
	
	@Override
	public RegistrationToken findByToken(String token){
		return registrationTokenRepository.findByToken(token);
	}
	
	/**
	 * Create an VerificationToken and return it updated 
	 * @param RegistrationToken
     * @return RegistrationToken
     */
	@Override
	public RegistrationToken createToken(User user, String token) {
		RegistrationToken myToken = new RegistrationToken(token, user);
		return registrationTokenRepository.save(myToken);
	}
	
	/**
	 * Delete an VerificationToken on database
	 * @param id as Integer
     */
	@Override
	public void delete(Integer id) {
		registrationTokenRepository.delete(id);
		
	}

	@Override
	public RegistrationToken saveOrUpdate(RegistrationToken domainObject) {
		return registrationTokenRepository.save(domainObject);
	}
	
	@Override
    public String validateVerificationToken(String token) {
        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token);
        if (registrationToken == null || registrationToken.isUsed()) {
            return TOKEN_INVALID;
        }

        User user = registrationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((registrationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        	registrationTokenRepository.delete(registrationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        userService.saveOrUpdate(user);
        registrationToken.setUsed(true);
        registrationToken = registrationTokenRepository.save(registrationToken);
        return TOKEN_VALID;
    }
}