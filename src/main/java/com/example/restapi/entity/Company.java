package com.example.restapi.entity;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private List<Employee> employees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee>  employee) {
        this.employees = employee;
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(){

    }
}
