package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.repositories.impl.UserRepositoryImpl;
import net.sports.nutrition.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 19:05
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepositoryImpl userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Before
    public void init() {
        when(userRepository.getUserById(new Long(3))).thenReturn(null);
    }

    @Test(expected = UserNotFoundException.class)
    public void testActivateUser() throws Exception {
        userServiceImpl.activateUser(new Long(3));
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeactivateUser() throws Exception {
        userServiceImpl.activateUser(new Long(3));
    }

    @Test(expected = UserNotFoundException.class)
    public void testSetUserRoleAdmin() throws Exception {
        userServiceImpl.activateUser(new Long(3));
    }
}