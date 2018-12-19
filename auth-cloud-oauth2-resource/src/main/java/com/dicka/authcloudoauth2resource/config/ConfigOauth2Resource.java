package com.dicka.authcloudoauth2resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigOauth2Resource extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeRequests()
                .anyRequest().permitAll()
                .and().cors().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authExeption)
                                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                ).accessDeniedHandler((request, response, authException)
                    -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceSecurity) throws Exception{
        resourceSecurity.resourceId("USER_ADMIN_RESOURCE")
                .tokenStore(tokenStore);
    }
}

