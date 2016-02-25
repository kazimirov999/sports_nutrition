package net.sports_nutrition.domain.repositories.repository_imlementations;

import net.sports_nutrition.domain.entities.User;
import net.sports_nutrition.domain.repositories.IUserRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 15:03
 */
@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Long> implements IUserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findUserByLogin(String login) {

        User user = null;

        try {
            user = (User) getSession()
                    .createCriteria(getType())
                    .add(Restrictions.eq("loginEmail", login))
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error getUserByLogin" + e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return getSession().createCriteria(getType()).list();
    }

    @Override
    public User getUserById(Long userId) {

        return (User) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("id", userId))
                .uniqueResult();
    }

}
