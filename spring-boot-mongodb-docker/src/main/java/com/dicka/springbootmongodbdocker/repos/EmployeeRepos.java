package com.dicka.springbootmongodbdocker.repos;

import com.dicka.springbootmongodbdocker.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepos extends MongoRepository<Employee, String> {
}
