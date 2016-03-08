package net.sports.nutrition.exceptions;

/**
 * @author  Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
