package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.User;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * The User Data Access Object is the interface providing
 * access to user and user type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IUserDao extends IGenericDao<User, Long> {

    /**
     * Returns  the user by login(Email).
     *
     * @param login -  user's login
     * @return <tt>User</tt> if the user is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    User findUserByLogin(String login);

    /**
     * Returns  all users.
     *
     * @return <tt>list of User</tt> if the users is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<User> findAllUsers() throws HibernateException;

    /**
     * Returns  the user by id.
     *
     * @param userId -  user's id
     * @return <tt>User</tt> if the user is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    User getUserById(Long userId) throws HibernateException;
}
