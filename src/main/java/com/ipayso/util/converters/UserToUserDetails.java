package com.ipayso.util.converters;

import com.ipayso.model.User;
import com.ipayso.model.security.UserDetailsImpl;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;

/**
 * UserToUserDetails.class -> Spring Framework needs an UserDetails implementation for internal configuration use,
 * 							  this class implements a Converter on our model User to convert it into a UserDetails
 * 							  class. The @Component annotation allow this class to be auto detected on Spring.
 * @author Cleber Oliveira
 * @version 1.0
 * @see Converter
 * @see User
 * @see UserDetails
 * @see @Component
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {
	
	/**
	 * Overrides the convert method passing an User as @param after set userDetails with User information the method @return userDetails
	 */
    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (user != null) {
            userDetails.setUsername(user.getEmail());
            userDetails.setPassword(user.getPassword());
            userDetails.setEnabled(user.getEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            userDetails.setAuthorities(authorities);
        }

        return userDetails;
    }
}