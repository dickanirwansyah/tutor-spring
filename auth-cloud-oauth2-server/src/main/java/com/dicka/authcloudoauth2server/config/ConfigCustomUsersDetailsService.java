package com.dicka.authcloudoauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dicka.authcloudoauth2server.entity.Users;
import com.dicka.authcloudoauth2server.repository.UsersRepository;

@Service(value = "userDetailsService")
public class ConfigCustomUsersDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username);
		
		if(users == null){
			throw new BadCredentialsException("BAD CREDENTIALS.");
		}
		
		new AccountStatusUserDetailsChecker()
			.check(users);
		return users;
	}

}
