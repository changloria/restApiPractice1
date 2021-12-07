package com.example.restapi;

import com.example.restapi.Exception.NoEmployeeFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        this.employees.add(new Employee(1, "Marcus", 22, "Male", 298912220));
    }

    public List<Employee> findAll() {
        return this.employees;
    }


    public Employee findById(Integer id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }
}
