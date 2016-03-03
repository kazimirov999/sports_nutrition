package net.sports.nutrition.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 15:24
 */
public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
