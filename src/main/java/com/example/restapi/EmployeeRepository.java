package com.example.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        this.employees.add(new Employee(1, "Marcus", 22, "Male", 298912220));
    }

    public List<Employee> findAll() {
        return this.employees;
    }
}
