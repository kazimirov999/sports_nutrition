package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.domain.enumx.Role;
import net.sports.nutrition.domain.dao.IUserDao;
import net.sports.nutrition.exceptions.UserNotFoundException;
import net.sports.nutrition.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to work with the User, using IUserDao.
 * .<p>
 * Implementation of IUserDao interface is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserByLogin(String login) {

        return userDao.findUserByLogin(login);
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User saveUser(User user) {

        return userDao.saveOrUpdate(user);
    }

    @Override
    public Boolean loginIsExist(String login) {

        return (findUserByLogin(login) != null);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    @Transactional(readOnly = false)
    @Override
    public void activateUser(Long userId) throws UserNotFoundException {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user is not exist");
        }
        user.setEnabled(true);
        userDao.edit(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deactivateUser(Long userId) throws UserNotFoundException {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user is not exist");
        }
        user.setEnabled(false);
        userDao.edit(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void setUserRoleAdmin(Long userId) throws UserNotFoundException {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user is not exist");
        }
        user.setRole(Role.ROLE_ADMIN);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteUserById(Long userId) {

        return userDao.delete(userDao.getUserById(userId));
    }
}
