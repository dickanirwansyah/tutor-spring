package com.dicka.demojpainventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoJpaInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaInventoryApplication.class, args);
	}

}

