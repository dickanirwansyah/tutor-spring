package com.dicka.authcloudoauth2server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dicka.authcloudoauth2server.entity.Users;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);
	
}
