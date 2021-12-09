package com.example.restapi.dto;

public class EmployeeRequest {
    private String name;
    private String id;
    private Integer age;
    private Integer salary;
    private String gender;
    private String companyID;

    public EmployeeRequest(String name, String id, Integer age, Integer salary, String gender, String companyID) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.companyID = companyID;
    }


    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}