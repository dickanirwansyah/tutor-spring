package com.dicka.authcloudoauth2server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicka.authcloudoauth2server.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findRoleById(Integer id);
	
}
