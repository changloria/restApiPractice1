package com.example.restapi.service;


import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeService employeeService;

    public CompanyService(CompanyRepository companyRepository, EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(String companyId) {
        Company company = companyRepository.findById(companyId);
        company.setEmployees(employeeService.findByCompanyId(companyId));
        return company;
    }

    public List<Employee> findEmployeeById(String id) {
        return companyRepository.findEmployeeById(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public Company save(String id, Company updatedCompany) {
        return companyRepository.save(id, updatedCompany);
    }

    public void delete(String companyId) {
        companyRepository.delete(companyId);
    }
}
