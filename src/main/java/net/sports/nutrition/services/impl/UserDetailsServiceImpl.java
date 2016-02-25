package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.User;
import net.sports.nutrition.domain.enumx.Role;
import net.sports.nutrition.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 15:58
 */

@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(userName);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        if (user == null) throw new UsernameNotFoundException("user not found");

        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails
            .User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {

        return new org.springframework.security.core.userdetails
                .User(user.getLoginEmail(),
                user.getPassword(), user.isEnabled(),
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role userRole) {
        Set<GrantedAuthority> setAuth = new HashSet<GrantedAuthority>();
        setAuth.add(new SimpleGrantedAuthority(userRole.toString()));
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuth);

        return Result;
    }
}
