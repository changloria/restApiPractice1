package com.example.restapi.service;


import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.exception.NoCompanyFoundException;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.repository.CompanyRepositoryNew;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private CompanyRepositoryNew companyRepositoryNew;
    private EmployeeService employeeService;

    public CompanyService(CompanyRepositoryNew companyRepositoryNew, CompanyRepository companyRepository, EmployeeService employeeService) {
        this.companyRepositoryNew = companyRepositoryNew;
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    public List<Company> findAll() {
        return companyRepositoryNew.findAll();
    }

    public Company findById(String companyId) {
        return companyRepositoryNew.findById(companyId).orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeeById(String companyId) {
        return employeeService.findEmployeesByCompanyId(companyId);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepositoryNew.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Company create(Company company) {
        return companyRepositoryNew.save(company);
    }

    public Company save(String id, Company updatedCompany) {
        Company company = companyRepositoryNew.findById(id).orElseThrow(NoCompanyFoundException::new);
        if (updatedCompany.getName() != null)
            company.setName(updatedCompany.getName());
        return companyRepositoryNew.save(company);
    }

    public void delete(String companyId) {
        companyRepositoryNew.deleteById(companyId);
    }
}
