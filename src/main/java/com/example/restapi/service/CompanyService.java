package com.example.restapi.service;


import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;

import java.util.List;

public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(int companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Employee> findEmployeeById(int id) {
        return companyRepository.findEmployeeById(id);
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return companyRepository.findByPage(page, pageSize);
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public Company save(int id, Company updatedCompany) {
        return companyRepository.save(id, updatedCompany);
    }

    public void delete(Integer companyId) {
        companyRepository.delete(companyId);
    }
}
