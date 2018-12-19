package com.dicka.authcloudoauth2server.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
	}
	
	
	/**
	@Bean
	public PasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	**/
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder)throws Exception {
		authBuilder.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(
			(request, response, authException) -> 
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
			.and().authorizeRequests()
			.antMatchers("/**")
			.authenticated().and()
			.httpBasic();
			
	}
}
