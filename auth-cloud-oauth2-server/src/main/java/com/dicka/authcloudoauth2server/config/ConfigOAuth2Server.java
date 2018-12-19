package com.dicka.authcloudoauth2server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class ConfigOAuth2Server extends AuthorizationServerConfigurerAdapter {

	@Value("${check-users-scopes}")
	private boolean checkUsersScopes;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter jwtConverter = new ConfigCustomTokenEnhancer();
		jwtConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), 
				"password".toCharArray()).getKeyPair("jwt"));
		return jwtConverter;
	}
	
	@Bean
	public OAuth2RequestFactory oauth2RequestFactory(){
		CustomOauth2RequestFactory oauth2RequestFactory = new CustomOauth2RequestFactory(clientDetailsService);
		oauth2RequestFactory.setCheckUserScopes(true);
		return oauth2RequestFactory;
	}
	
	@Bean
	public TokenStore tokenStore(){
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clientsConfigure) throws Exception{
		clientsConfigure.jdbc(dataSource)
			.passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter(){
		return new TokenEndpointAuthenticationFilter(authenticationManager, oauth2RequestFactory());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer authServerConfig) throws Exception{
		authServerConfig.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authServerEndpoints) throws Exception{
		authServerEndpoints.tokenStore(tokenStore())
			.tokenEnhancer(jwtAccessTokenConverter())
				.authenticationManager(authenticationManager)
					.userDetailsService(userDetailsService);
		
		if(checkUsersScopes)
			authServerEndpoints.requestFactory(oauth2RequestFactory());
	}
}
