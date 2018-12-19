package com.dicka.authcloudoauth2server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dicka.authcloudoauth2server.entity.Role;
import com.dicka.authcloudoauth2server.entity.Users;
import com.dicka.authcloudoauth2server.repository.RoleRepository;
import com.dicka.authcloudoauth2server.repository.UsersRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthCloudOauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCloudOauth2ServerApplication.class, args);
	}

}
/**
@Component
class ExampleCreateData implements CommandLineRunner{

	@Autowired 
	private UsersRepository usersRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		try{
		
			Role role = roleRepository.findRoleById(1);
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
			
			Users users = new Users();
			users.setEmail("dickanirwansyah@gmail.com");
			users.setUsername("dickanirwansyah");
			users.setPassword(passwordEncoder.encode("secret"));
			users.setRoles(roles);;
			users.setEnabled(true);
			
			usersRepository.save(users);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
**/
