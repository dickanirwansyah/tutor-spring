package com.dicka.springbootupload.uploads.service;

import com.dicka.springbootupload.uploads.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    List<Employee> getAll();

}
