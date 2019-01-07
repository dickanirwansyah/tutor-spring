package com.dicka.onlinebankingexample.service.impl;

import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService{

    /** log **/
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username "+username+" not found");
        }
        return user;
    }
}
