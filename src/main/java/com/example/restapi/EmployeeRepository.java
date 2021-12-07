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
        this.employees.add(new Employee(2, "Marcus2", 22, "Male", 298912220));
        this.employees.add(new Employee(3, "Marcus3", 22, "Male", 298912220));
        this.employees.add(new Employee(4, "Marcus4", 22, "Male", 298912220));
        this.employees.add(new Employee(5, "Marcus5", 22, "Male", 298912220));
        this.employees.add(new Employee(6, "Marcus6", 22, "Male", 298912220));
        this.employees.add(new Employee(7, "Marcus7", 22, "Male", 298912220));
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

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employees.stream().skip((long) (page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Employee create(Employee newEmployee) {
        Integer nextId = employees.stream().mapToInt(Employee::getId).max().orElse(0) + 1;
        newEmployee.setId(nextId);
        this.employees.add(newEmployee);
        return newEmployee;
    }

    public Employee save(Integer id, Employee updatedEmployee) {
        Employee employee = findById(id);
        employees.remove(employee);
        employees.add(updatedEmployee);
        return updatedEmployee;
    }

    public void delete(Integer id) {
        Employee employee = findById(id);
        employees.remove(employee);
    }
}
