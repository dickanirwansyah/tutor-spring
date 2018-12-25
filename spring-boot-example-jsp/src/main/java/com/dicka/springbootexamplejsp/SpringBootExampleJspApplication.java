package com.dicka.springbootexamplejsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootExampleJspApplication extends SpringBootServletInitializer{
	
	/** jika format packaging-nya war wajib tambahkan ini **/
	/** dan extends SpringBootServletInitalizer**/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SpringBootExampleJspApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleJspApplication.class, args);
	}

}

