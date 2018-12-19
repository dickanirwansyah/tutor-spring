package com.dicka.authcloudoauth2resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthCloudOauth2ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCloudOauth2ResourceApplication.class, args);
	}

}

