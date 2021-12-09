package com.example.restapi.mapper;

import com.example.restapi.dto.CompanyRequest;
import com.example.restapi.dto.CompanyResponse;
import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    public CompanyMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    private EmployeeMapper employeeMapper;

    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();

        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company, List<Employee> employees){
        CompanyResponse companyResponse = new CompanyResponse();

        BeanUtils.copyProperties(company, companyResponse);
        companyResponse.setEmployeeResponses(
                employees.stream()
                        .map(employee -> employeeMapper.toResponse(employee))
                        .collect(Collectors.toList())
        );

        return companyResponse;
    }
}
