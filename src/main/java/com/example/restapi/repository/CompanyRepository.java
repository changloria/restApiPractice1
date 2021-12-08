package com.example.restapi.repository;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {

    private List<Company> companies = new ArrayList<>();

    List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "Marcus", 22, "Male", 298912220), new Employee(12, "Marcus2", 22, "Male", 298912220)));

    public CompanyRepository() {
        this.companies.add(new Company(1, "Spring", employees));
        this.companies.add(new Company(2, "Spring2", employees));
        this.companies.add(new Company(3, "Spring3", employees));
    }

    public List<Company> findAll() {
        return this.companies;
    }

    public Company findById(Integer id) {
        return companies.stream().filter(company -> company.getId().equals(id)).findFirst().orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeeById(Integer id) {
        return findById(id).getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companies.stream().skip((long) (page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
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
