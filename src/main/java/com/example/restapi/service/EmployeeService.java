package com.example.restapi.service;

import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoEmployeeFoundException;
import com.example.restapi.repository.EmployeeRepository;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeRepositoryNew employeeRepositoryNew;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeRepositoryNew employeeRepositoryNew) {
        this.employeeRepository = employeeRepository;
        this.employeeRepositoryNew = employeeRepositoryNew;
    }

    public List<Employee> findAll() {
        return employeeRepositoryNew.findAll();
    }

    public Employee edit(String id, Employee updatedEmployee) {
        Employee employee = findById(id);
        if (updatedEmployee.getAge() != null) {
            employee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            employee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepositoryNew.save(employee);
    }

    public Employee findById(String id) {
        return employeeRepositoryNew.findById(id).orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepositoryNew.findAllByGender(gender);
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employeeRepositoryNew.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public List<Employee> findByCompanyId(String companyId) {
        return employeeRepositoryNew.findAllByCompanyId(companyId);
    }

    public Employee create(Employee newEmployee) {
        return employeeRepositoryNew.insert(newEmployee);
    }

    public void delete(String id) {
        employeeRepositoryNew.deleteById(id);
    }
}
