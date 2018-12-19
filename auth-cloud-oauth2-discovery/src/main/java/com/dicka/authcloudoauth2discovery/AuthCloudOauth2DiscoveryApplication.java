package com.dicka.authcloudoauth2discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AuthCloudOauth2DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCloudOauth2DiscoveryApplication.class, args);
	}

}

