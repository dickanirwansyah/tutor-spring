package com.dicka.springbootjmsactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootJmsActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsActivemqApplication.class, args);
	}

}

