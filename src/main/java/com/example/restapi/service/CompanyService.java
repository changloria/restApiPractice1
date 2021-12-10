package com.example.restapi.service;


import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
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
        return companyRepository.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeeById(String companyId) {
        return employeeService.findEmployeesByCompanyId(companyId);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company save(String id, Company updatedCompany) {
        Company company = companyRepository.findById(id).orElseThrow(NoCompanyFoundException::new);
        if (updatedCompany.getName() != null)
            company.setName(updatedCompany.getName());
        return companyRepository.save(company);
    }

    public void delete(String companyId) {
        companyRepository.deleteById(companyId);
    }
}
