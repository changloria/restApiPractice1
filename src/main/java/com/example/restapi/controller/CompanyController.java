package com.example.restapi.controller;

import com.example.restapi.dto.CompanyRequest;
import com.example.restapi.dto.CompanyResponse;
import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.mapper.CompanyMapper;
import com.example.restapi.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {

    private CompanyMapper companyMapper;
    private CompanyService companyService;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable String id) {
        Company company = companyService.findById(id);
        List<Employee> employees = companyService.findEmployeeById(id);
        return companyMapper.toResponse(company, employees);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable String id) {
        return companyService.findEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getAllCompaniesByPage(@RequestParam Integer page, Integer pageSize) {
        return companyService.findByPage(page, pageSize);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Company createCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.create(companyMapper.toEntity(companyRequest));
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable String id, @RequestBody Company updatedCompany) {
        Company company = companyService.findById(id);

        if (updatedCompany.getName() != null) {
            company.setName(updatedCompany.getName());
        }
        return companyService.save(id, company);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable String id) {
        Company company = companyService.findById(id);
        companyService.delete(company.getId());
    }
}
