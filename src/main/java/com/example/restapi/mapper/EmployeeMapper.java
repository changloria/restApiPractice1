package com.example.restapi.mapper;

import com.example.restapi.dto.EmployeeRequest;
import com.example.restapi.dto.EmployeeResponse;
import com.example.restapi.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
//        BeanUtils.copyProperties(employeeRequest, employee);
        employee.setName(employeeRequest.getName());
        employee.setId(employeeRequest.getId());
        employee.setAge(employeeRequest.getAge());
        employee.setGender(employeeRequest.getGender());
        employee.setSalary(employeeRequest.getSalary());
        employee.setCompanyId(employeeRequest.getCompanyID());

        return employee;
    }
    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

}
