package com.dicka.springbootdtoangularjs.repository;

import com.dicka.springbootdtoangularjs.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
