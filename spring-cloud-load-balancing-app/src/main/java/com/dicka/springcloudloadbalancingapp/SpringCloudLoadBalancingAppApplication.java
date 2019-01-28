package com.dicka.springcloudloadbalancingapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/** spring-cloud-load-balancing-web --> adalah nama service yang
 * terdaftar pada service registry atau eureka
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "SPRING-CLOUD-LOAD-BALANCING-WEB",
		configuration = ClientAppConfiguration.class)
@RestController
public class SpringCloudLoadBalancingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLoadBalancingAppApplication.class, args);
	}


	@Bean
	@LoadBalanced
	public RestTemplate restTemplateClient(){
		return new RestTemplate();
	}

	/**
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	**/

	@Autowired
	RestTemplate restTemplate;


	@GetMapping(value = "/my-app")
	public String getHalloFromRibbonClient(){
		return this.restTemplate
				.getForObject("http://spring-cloud-load-balancing-web/hello-world",
						String.class);
	}
}

