package com.example.restapi.bo;

import com.example.restapi.entity.Employee;

import java.util.List;

public class CompanyWithEmployee {
    private String id;
    private String name;
    private List<Employee> employeeList;

    public CompanyWithEmployee(String id, String name, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.employeeList = employeeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
