package com.dicka.onlinebankingexample.service;

import com.dicka.onlinebankingexample.entity.User;
import com.dicka.onlinebankingexample.entity.secure.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUserExists(String username, String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);

    void save(User user);
    User createUser(User user, Set<UserRole> userRoles);
    User saveUser(User user);
    List<User> findUserList();

    void enableUser(String username);
    void disable(String username);
}
