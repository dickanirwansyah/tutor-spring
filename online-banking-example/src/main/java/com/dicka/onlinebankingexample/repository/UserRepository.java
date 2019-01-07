package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User findByUserId(Long userId);
}
