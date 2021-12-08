package com.example.restapi.repository;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    @Autowired
    EmployeeRepository employeeRepository;
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        this.companies.add(new Company(1, "Spring"));
        this.companies.add(new Company(2, "Spring2"));
        this.companies.add(new Company(3, "Spring3"));
    }

    public List<Company> findAll() {
        companies.forEach(company -> company.setEmployees(employeeRepository.findByCompanyId(company.getId())));
        return this.companies;
    }

    public Company findById(Integer id) {
        Company company = companies.stream().filter(company1 -> company1.getId().equals(id)).findFirst().orElseThrow(NoCompanyFoundException::new);
        company.setEmployees(employeeRepository.findByCompanyId(company.getId()));
        return company;
    }

    public List<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findByCompanyId(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        List<Company> companies = this.companies.stream().skip((long) (page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        companies.forEach(company -> {company.setEmployees(employeeRepository.findByCompanyId(company.getId()));});
        return companies;
    }

    public Company create(Company newCompany) {
        Integer nextId = companies.stream().mapToInt(Company::getId).max().orElse(0) + 1;
        newCompany.setId(nextId);
        this.companies.add(newCompany);
        return newCompany;
    }


    public Company save(Integer id, Company updatedCompany) {
        Company company = findById(id);
        companies.remove(company);
        companies.add(updatedCompany);
        return updatedCompany;
    }

    public void delete(Integer id) {
        Company company = findById(id);
        companies.remove(company);
    }

    public void clearAll() {
        companies.clear();
    }
}
