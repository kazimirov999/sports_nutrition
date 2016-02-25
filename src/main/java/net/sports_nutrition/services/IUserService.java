package net.sports_nutrition.services;

import net.sports_nutrition.domain.entities.User;

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

    void activateUser(Long userId);

    void deactivateUser(Long userId);

    void setUserRoleAdmin(Long userId);

    Boolean deleteUserById(Long userId);
}
