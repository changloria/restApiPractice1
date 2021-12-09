package com.example.restapi.repository;

import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoEmployeeFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();


    public EmployeeRepository() {
        this.employees.add(new Employee("1", "Marcus", 19, "Male", 1920213, "1"));
        this.employees.add(new Employee("2", "Gloria2", 22, "Female", 10000,"1"));
        this.employees.add(new Employee("3", "Gloria3", 22, "Female", 10000,"1"));
        this.employees.add(new Employee("4", "Gloria4", 22, "Female", 10000,"1"));
        this.employees.add(new Employee("5", "Gloria5", 22, "Female", 10000,"1"));
        this.employees.add(new Employee("6", "Gloria6", 22, "Female", 10000,"1"));
        this.employees.add(new Employee("7", "Gloria7", 22, "Female", 10000,"1"));
    }

    public List<Employee> findAll() {
        return this.employees;
    }


    public Employee findById(String id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employees.stream().skip((long) (page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Employee create(Employee newEmployee) {
        String nextId = String.valueOf(employees.size() + 1);
        newEmployee.setId(nextId);
        this.employees.add(newEmployee);
        return newEmployee;
    }

    public Employee save(String id, Employee updatedEmployee) {
        Employee employee = findById(id);
        employees.remove(employee);
        employees.add(updatedEmployee);
        return updatedEmployee;
    }

    public void delete(String id) {
        Employee employee = findById(id);
        employees.remove(employee);
    }

    public void clearAll() {
        employees.clear();
    }

    public List<Employee> findByCompanyId(String companyId) {
        return employees.stream().filter(employee -> employee.getCompanyId().equals(companyId)).collect(Collectors.toList());
    }
}
