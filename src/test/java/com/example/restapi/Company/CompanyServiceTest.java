package com.example.restapi.Company;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;
import com.example.restapi.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository mockCompanyRepository;

    @InjectMocks
    CompanyService companyService;

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Gloria1", 22, "Female", 100000, 1));
        employees.add(new Employee(2, "Gloria2", 22, "Female", 100000, 1));
        employees.add(new Employee(3, "Gloria3", 22, "Female", 100000, 1));
        employees.add(new Employee(4, "Gloria4", 22, "Female", 100000, 1));
        employees.add(new Employee(5, "Gloria5", 22, "Female", 100000, 1));
        employees.add(new Employee(6, "Gloria6", 22, "Female", 100000, 1));
        employees.add(new Employee(7, "Gloria7", 22, "Female", 100000, 1));
        return employees;
    }

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Company> companies = new ArrayList<>();
        given(mockCompanyRepository.findAll())
                .willReturn(companies);
        //when
        List<Company> actual = companyService.findAll();
        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_get_company_when_perform_getById_given_company_and_id() throws Exception {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL"));
        companies.add(new Company(2, "OOCL2"));

        given(mockCompanyRepository.findById(1))
                .willReturn(companies.get(0));

        //when
        Company actual = companyService.findById(1);
        //then
        assertEquals(companies.get(0), actual);
    }

    @Test
    void should_get_all_employee_under_company_when_obtain_employee_list_given_employees_and_company() throws Exception {
        //given
        List<Employee> employees = getEmployees();
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL"));
        companies.add(new Company(2, "OOCL2"));

        given(mockCompanyRepository.findEmployeeById(1))
                .willReturn(employees);
        //when`
        List<Employee> actual = companyService.findEmployeeById(1);
        //then
        assertEquals(employees, actual);

    }
}
