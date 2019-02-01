package com.dicka.springbootupload.uploads.repository;

import com.dicka.springbootupload.uploads.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
