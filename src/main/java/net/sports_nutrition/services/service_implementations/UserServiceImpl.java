package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.domain.entities.User;
import net.sports_nutrition.domain.enumx.Role;
import net.sports_nutrition.domain.repositories.IUserRepository;
import net.sports_nutrition.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 15:26
 */

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findUserByLogin(String login) {

        return userRepository.findUserByLogin(login);
    }

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        return userRepository.saveOrUpdate(user);
    }

    @Override
    public Boolean loginIsExist(String login) {

        return (findUserByLogin(login) != null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void activateUser(Long userId) {
        String serviceMessage = "activate.success";
        User user = userRepository.getUserById(userId);
        if (user == null) new Exception("user not exist");
        user.setEnabled(true);
        userRepository.edit(user);
    }

    @Override
    public void deactivateUser(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) new Exception("user not exist");
        user.setEnabled(false);
        userRepository.edit(user);
    }

    @Transactional
    @Override
    public void setUserRoleAdmin(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) new Exception("user not exist");
        user.setRole(Role.ROLE_ADMIN);
    }

    @Transactional
    @Override
    public Boolean deleteUserById(Long userId) {

        return userRepository.delete(userRepository.getUserById(userId));
    }
}
