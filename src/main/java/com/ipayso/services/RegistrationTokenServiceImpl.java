package com.ipayso.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipayso.model.RegistrationToken;
import com.ipayso.model.User;
import com.ipayso.repositories.RegistrationTokenRepository;
import com.ipayso.util.enums.TokenStatus;

/**
 * RegistrationTokenServiceImpl.class -> This Service offers a RegistrationTokenServiceImpl implementation to persist the basics on a database. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see VerificationTokenService
 */
@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService{
	
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
	 * @see CRUDService listAll method
	 */
	@Override
	public List<?> listAll() {
		List<RegistrationToken> tokens = new ArrayList<>();
        registrationTokenRepository.findAll().forEach(tokens::add);
        return tokens;
	}
	
	/**
	 * @see CRUDService getById method
	 */
	@Override
	public RegistrationToken getById(Integer id) {
		return registrationTokenRepository.findOne(id);
	}
	
	/**
	 * @see CRUDService saveOrUpdate method
	 */
	@Override
	public RegistrationToken saveOrUpdate(RegistrationToken domainObject) {
		return registrationTokenRepository.save(domainObject);
	}
	
	/**
	 * @see CRUDService delete method
	 */
	@Override
	public void delete(Integer id) {
		registrationTokenRepository.delete(id);
	}


	/**
	 * @see RegistrationTokenService createToken method
	 */
	@Override
	public RegistrationToken createToken(User user, String token) {
		RegistrationToken myToken = new RegistrationToken(token, user);
		return registrationTokenRepository.save(myToken);
	}
	
	/**
	 * @see RegistrationTokenService createToken method
	 */
	@Override
	public RegistrationToken getByToken(String token){
		return registrationTokenRepository.findByToken(token);
	}
	
	/**
	 * @see RegistrationTokenService createToken method
	 */
	@Override
    public RegistrationToken generateNewVerificationToken(String existingVerificationToken) {
		RegistrationToken vToken = registrationTokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = registrationTokenRepository.save(vToken);
        return vToken;
    }
	
	/**
	 * @see RegistrationTokenService createToken method
	 */
	@Override
    public String validateVerificationToken(String token) {
        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token);
        if (registrationToken == null || registrationToken.isUsed()) {
            return TokenStatus.TOKEN_INVALID.getDescription();
        }

        User user = registrationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((registrationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        	registrationTokenRepository.delete(registrationToken);
            return TokenStatus.TOKEN_EXPIRED.getDescription();
        }

        user.setEnabled(true);
        userService.saveOrUpdate(user);
        registrationToken.setUsed(true);
        registrationToken = registrationTokenRepository.save(registrationToken);
        return TokenStatus.TOKEN_VALID.getDescription();
    }
}