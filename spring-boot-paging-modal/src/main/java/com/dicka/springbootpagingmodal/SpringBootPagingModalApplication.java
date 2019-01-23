package com.dicka.springbootpagingmodal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;


@SpringBootApplication
public class SpringBootPagingModalApplication {

	final static Logger log = LoggerFactory.getLogger(SpringBootPagingModalApplication.class);

	public SpringBootPagingModalApplication(){
		log.info("inside app constructor creating instance "+ DateFormat.getInstance()
				.format(new Date()));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPagingModalApplication.class, args);

	}

}

