package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.exceptions.UserNotFoundException;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Service to work with the User.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IUserService {

    /**
     * Returns  the user by login(Email).
     *
     * @param login -  user's login
     * @return <tt>User</tt> if the user is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    User findUserByLogin(String login);

    /**
     * Saves user.
     *
     * @param user - user for save.
     * @return <tt>User<tt/> if the action is successful, throw exception  otherwise.
     */
    User saveUser(User user);

    /**
     * Checks user of the presence in the database.
     *
     * @param login - login for check
     * @return <tt>true</tt> if login is exist, <tt>false</tt> otherwise
     */
    Boolean loginIsExist(String login);

    /**
     * Returns  all users.
     *
     * @return <tt>list of User</tt> if the users is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<User> getAllUsers();

    /**
     * Activates user
     *
     * @param userId - identifier of the user
     * @throws UserNotFoundException - if user not found
     */
    void activateUser(Long userId) throws UserNotFoundException;

    /**
     * Deactivates user
     *
     * @param userId - identifier of the user
     * @throws UserNotFoundException - if user not found
     */
    void deactivateUser(Long userId) throws UserNotFoundException;

    /**
     * Sets user's role admin
     *
     * @param userId - identifier of the user
     * @throws UserNotFoundException - if user not found
     */
    void setUserRoleAdmin(Long userId) throws UserNotFoundException;

    /**
     * Deletes  user by id.
     *
     * @param userId - identifier of the user
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     */
    Boolean deleteUserById(Long userId);
}
