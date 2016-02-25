package net.sports.nutrition.domain.repositories;

        import net.sports.nutrition.domain.entities.User;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 15:02
 */
public interface IUserRepository extends IGenericRepository<User, Long> {

    User findUserByLogin(String login);

    List<User> getAllUsers();

    @Transactional
    User getUserById(Long userId);
}
