package com.gmail.petrikov05.app.service.impl;

import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.model.AppUser;
import com.gmail.petrikov05.app.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityDetailsService implements UserDetailsService {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(SecurityDetailsService.class);

    public SecurityDetailsService(UserService userService) {this.userService = userService;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        logger.debug("'User '" + username + "' was found'");
        return new AppUser(user);
    }

}
