package com.dicka.springbootpagingmodal;


import com.dicka.springbootpagingmodal.entity.Country;
import com.dicka.springbootpagingmodal.entity.Kategori;
import com.dicka.springbootpagingmodal.repository.CountryRepository;
import com.dicka.springbootpagingmodal.repository.KategoriRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;


@SpringBootApplication
public class SpringBootPagingModalApplication {

	final static Logger log = LoggerFactory.getLogger(SpringBootPagingModalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPagingModalApplication.class, args);

	}
}

/**
@Component
class TestCommandLineRunner implements CommandLineRunner{

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void run(String... args) throws Exception {
		countryRepository.save(new Country("Indonesia", "Asia"));
		countryRepository.save(new Country("United State of America","USA"));
		countryRepository.save(new Country("Palestina", "UAE"));
		countryRepository.save(new Country("Pakistan", "UAE"));
		countryRepository.save(new Country("Polandia", "Europe"));
		countryRepository.save(new Country("German", "Europe"));
		countryRepository.save(new Country("Japan", "Asia"));
		countryRepository.save(new Country("Malaysia", "Asia"));
		countryRepository.save(new Country("Thailand", "Asia"));
		countryRepository.save(new Country("Vietnam", "Asia"));
		countryRepository.save(new Country("France", "Europe"));
		countryRepository.save(new Country("India", "Asia"));
		countryRepository.save(new Country("Egypt", "Afrika"));
		countryRepository.save(new Country("Ghana", "Afrika"));
		countryRepository.save(new Country("Turki", "Asia"));
		countryRepository.save(new Country("Korea Selatan", "Asia"));
		countryRepository.save(new Country("Korea Utara", "Asia"));
		countryRepository.save(new Country("Brazil", "Amerika"));
		countryRepository.save(new Country("Chile", "Amerika"));
		countryRepository.save(new Country("Kanada", "Amerika"));
	}
}
 **/
