package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.secure.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByName(String name);
}
