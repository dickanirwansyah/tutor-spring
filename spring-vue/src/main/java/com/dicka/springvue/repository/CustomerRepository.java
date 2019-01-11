package com.dicka.springvue.repository;

import com.dicka.springvue.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByAge(int age);

    Customer findCustomerById(Long id);

}
