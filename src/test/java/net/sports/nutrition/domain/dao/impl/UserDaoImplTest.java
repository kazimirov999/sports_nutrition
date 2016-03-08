package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.domain.dao.IUserDao;
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
public class UserDaoImplTest {

    @Autowired
    private IUserDao userDao;

    @Test
    @Rollback(true)
    public void testFindUserByLogin() throws Exception {
        User user = userDao.findUserByLogin("simpleUser@mail.ru");
        assertNotNull(user);
        assertEquals("simpleUser@mail.ru", user.getLoginEmail());
    }

    @Test
    @Rollback(true)
    public void testSave() throws Exception {
        User user = new User();
        user.setFirstName("Nik");
        user.setLoginEmail("mail@mk.net");
        User userSave = userDao.save(user);
        assertNotNull(userSave);
        assertEquals("mail@mk.net", userSave.getLoginEmail());
        assertNotNull(userDao.findUserByLogin("mail@mk.net"));
    }

    @Test
    @Rollback(true)
    public void testEdit() throws Exception {
        User user = userDao.findUserByLogin("kazimirov@ro.ru");
        user.setLoginEmail("test@test.net");
        User userEdit = userDao.edit(user);
        assertNotNull(userEdit);
        assertEquals("test@test.net", userEdit.getLoginEmail());
        assertNotNull(userDao.findUserByLogin("test@test.net"));
        assertNull(userDao.findUserByLogin("kazimirov@ro.ru"));
    }

    @Test
    @Rollback(true)
    public void testGetAllUsers() throws Exception {
        List<User> users = userDao.findAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Alexandr", users.get(0).getFirstName());
        assertEquals("Easy", users.get(1).getFirstName());

    }

    @Test
    @Rollback(true)
    public void testGetUserById() throws Exception {
        User user = userDao.getUserById(new Long(2));
        assertNotNull(user);
        assertEquals("Easy", user.getFirstName());
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception {
        User user = userDao.findUserByLogin("simpleUser@mail.ru");
        Boolean result = userDao.delete(user);
        assertNotNull(result);
        assertTrue(result);
        assertNull(userDao.findUserByLogin("simpleUser@mail.ru"));
    }
}