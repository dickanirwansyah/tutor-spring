package com.dicka.springcloudloadbalancingeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLoadBalancingEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLoadBalancingEurekaApplication.class, args);
	}

}

