package com.dicka.springcloudloadbalancingweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudLoadBalancingWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLoadBalancingWebApplication.class, args);
	}

	@Value("${server.port}")
	private String port;

	@GetMapping(value = "/hello-world")
	public String getHallo(){
		return "response hello world from port : "+port;
	}

	@GetMapping(value = "I")
	public String getHome(){
		return "Okay";
	}
}

