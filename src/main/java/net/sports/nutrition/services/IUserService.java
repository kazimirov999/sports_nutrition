package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 15:25
 */
public interface IUserService {

    User findUserByLogin(String login);

    User saveUser(User user);

    Boolean loginIsExist(String login);

    List<User> getAllUsers();

    void activateUser(Long userId) throws UserNotFoundException;

    void deactivateUser(Long userId) throws UserNotFoundException;

    void setUserRoleAdmin(Long userId) throws UserNotFoundException;

    Boolean deleteUserById(Long userId);
}
