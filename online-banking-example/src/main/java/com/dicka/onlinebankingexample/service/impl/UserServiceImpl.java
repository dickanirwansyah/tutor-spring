package com.dicka.onlinebankingexample.service.impl;

import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.entity.secure.UserRole;
import com.dicka.onlinebankingexample.repository.RoleRepository;
import com.dicka.onlinebankingexample.repository.UserRepository;
import com.dicka.onlinebankingexample.service.AccountService;
import com.dicka.onlinebankingexample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        if (checkUsernameExists(username) || checkEmailExists(email)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)){
            return true;
        }
        return false;
    }

    @Override
    public void save(User user)
    {
        userRepository.save(user);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null){
            LOG.info("user with username {} is already exists", user.getUsername());
        }else {

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            user.setEnable(true);
            user.getUserRoles().addAll(userRoles);
            /** save primary account **/
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            /** save savings account **/
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUserList() {
        List<User> users = new ArrayList<>();
        for (User user: userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

    /** enable user **/
    @Override
    public void enableUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setEnable(true);
        userRepository.save(user);
    }

    /** disable user **/
    @Override
    public void disable(String username) {
        User user = userRepository.findByUsername(username);
        user.setEnable(false);
        userRepository.save(user);
    }
}
