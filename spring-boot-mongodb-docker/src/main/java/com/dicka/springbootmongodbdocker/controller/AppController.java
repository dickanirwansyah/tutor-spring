package com.dicka.springbootmongodbdocker.controller;

import com.dicka.springbootmongodbdocker.model.Employee;
import com.dicka.springbootmongodbdocker.repos.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ConfigurationProperties
@Component
public class AppController {

    @Autowired
    private EmployeeRepos employeeRepos;

    @GetMapping(value = "/")
    public String hello(){
        return "Hello docker + spring boot + mongodb";
    }

    @PostMapping(value = "/employee")
    public Employee create(@RequestBody Employee employee){
        return employeeRepos.save(employee);
    }

    @GetMapping(value = "/employee/{id}")
    public Optional<Employee> get(@PathVariable("id") String id){
        return employeeRepos.findById(id);
    }

}
