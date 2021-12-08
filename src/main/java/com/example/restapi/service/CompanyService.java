package com.example.restapi.service;


import com.example.restapi.entity.Company;
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
        return null;
    }
}
