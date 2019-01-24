package com.dicka.springbootpagingmodal.repository;

import com.dicka.springbootpagingmodal.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findCountryById(int id);

}
