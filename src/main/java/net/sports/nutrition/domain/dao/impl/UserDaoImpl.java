package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.dao.IUserDao;
import net.sports.nutrition.domain.entities.User;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The User Data Access Object is the class providing
 * access to user and user type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements IUserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
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
            log.error("Find user error", e);
        }

        return user;
    }

    @Override
    public List<User> findAllUsers() {

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
