package com.dicka.springbootupload.uploads.service;

import com.dicka.springbootupload.uploads.Employee;
import com.dicka.springbootupload.uploads.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()){
            employees.add(employee);
        }
        return employees;
    }
}
