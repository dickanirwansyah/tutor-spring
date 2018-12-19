package com.dicka.authcloudoauth2server.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.dicka.authcloudoauth2server.entity.Users;

public class ConfigCustomTokenEnhancer extends JwtAccessTokenConverter{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
			OAuth2Authentication authentication){
		
		Users users = (Users) authentication.getPrincipal();
		Map<String, Object> info = new LinkedHashMap<String, Object>(
				accessToken.getAdditionalInformation());
		info.put("email", users.getEmail());
		DefaultOAuth2AccessToken defaultOauth2AccessToken = new 
				DefaultOAuth2AccessToken(accessToken);
		defaultOauth2AccessToken.setAdditionalInformation(info);
		return super.enhance(defaultOauth2AccessToken, authentication);
	}
	
}
