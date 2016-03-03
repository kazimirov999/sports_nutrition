package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.domain.repositories.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 28.02.2016 22:02
 */

@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryImplTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    @Rollback(true)
    public void testFindUserByLogin() throws Exception {
        User user = userRepository.findUserByLogin("simpleUser@mail.ru");
        assertNotNull(user);
        assertEquals("simpleUser@mail.ru", user.getLoginEmail());
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        User user = new User();
        user.setFirstName("Nik");
        user.setLoginEmail("mail@mk.net");
        User userSave = userRepository.save(user);
        assertNotNull(userSave);
        assertEquals("mail@mk.net", userSave.getLoginEmail());
        assertNotNull(userRepository.findUserByLogin("mail@mk.net"));
    }

    @Test
    @Rollback(true)
    public void testEdit() throws Exception {
        User user = userRepository.findUserByLogin("kazimirov@ro.ru");
        user.setLoginEmail("test@test.net");
        User userEdit = userRepository.edit(user);
        assertNotNull(userEdit);
        assertEquals("test@test.net", userEdit.getLoginEmail());
        assertNotNull(userRepository.findUserByLogin("test@test.net"));
        assertNull(userRepository.findUserByLogin("kazimirov@ro.ru"));
    }

    @Test
    @Rollback(true)
    public void testGetAllUsers() throws Exception {
        List<User> users = userRepository.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Alexandr", users.get(0).getFirstName());
        assertEquals("Easy", users.get(1).getFirstName());

    }

    @Test
    @Rollback(true)
    public void testGetUserById() throws Exception {
        User user = userRepository.getUserById(new Long(2));
        assertNotNull(user);
        assertEquals("Easy", user.getFirstName());
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        User user = userRepository.findUserByLogin("simpleUser@mail.ru");
        Boolean result = userRepository.delete(user);
        assertNotNull(result);
        assertTrue(result);
        assertNull(userRepository.findUserByLogin("simpleUser@mail.ru"));
    }
}