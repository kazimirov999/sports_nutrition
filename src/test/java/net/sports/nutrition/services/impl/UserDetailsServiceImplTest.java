package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.domain.enumx.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 29.02.2016 15:31
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private User user;

    @Test
    public void testLoadUserByUsername() throws Exception {
        when(user.getLoginEmail()).thenReturn("nik@n.net");
        when(user.getRole()).thenReturn(Role.ROLE_ADMIN);
        when(user.getPassword()).thenReturn("1111");
        when(user.isEnabled()).thenReturn(true);

        when(userService.findUserByLogin("nik@n.net")).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername("nik@n.net");
        assertNotNull(userDetails);
        assertEquals("nik@n.net", userDetails.getUsername());
        assertEquals("1111", userDetails.getPassword());
        assertEquals(true, userDetails.isEnabled());
        Collection<? extends GrantedAuthority> grantedAuthority = userDetails.getAuthorities();
        assertNotNull(grantedAuthority);
        String role = grantedAuthority.iterator().next().getAuthority();
        assertNotNull(role);
        assertEquals("ROLE_ADMIN", role);
    }
}