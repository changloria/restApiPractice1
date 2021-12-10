package com.example.restapi.service;

import com.example.restapi.entity.Company;
import com.example.restapi.entity.Employee;
import com.example.restapi.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @Mock
    EmployeeService mockEmployeeService;

    @InjectMocks
    CompanyService companyService;

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Gloria1", 22, "Female", 100000, "1"));
        employees.add(new Employee("2", "Gloria2", 22, "Female", 100000, "1"));
        employees.add(new Employee("3", "Gloria3", 22, "Female", 100000, "1"));
        employees.add(new Employee("4", "Gloria4", 22, "Female", 100000, "1"));
        employees.add(new Employee("5", "Gloria5", 22, "Female", 100000, "1"));
        employees.add(new Employee("6", "Gloria6", 22, "Female", 100000, "1"));
        employees.add(new Employee("7", "Gloria7", 22, "Female", 100000, "1"));
        return employees;
    }

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Company> companies = new ArrayList<>();
        given(companyRepository.findAll())
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
        companies.add(new Company("1", "OOCL"));
        companies.add(new Company("2", "OOCL2"));

        given(companyRepository.findById("1"))
                .willReturn(java.util.Optional.ofNullable(companies.get(0)));
        given(mockEmployeeService.findEmployeesByCompanyId("1"))
                .willReturn(getEmployees());

        //when
        Company actual = companyService.findById(companies.get(0).getId());

        //then
        assertEquals(companies.get(0).getEmployees(), actual.getEmployees());
    }

    @Test
    void should_get_all_employee_under_company_when_obtain_employee_list_given_employees_and_company() throws Exception {
        //given
        List<Employee> employees = getEmployees();
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("1", "OOCL"));
        companies.add(new Company("2", "OOCL2"));

        given(mockEmployeeService.findEmployeesByCompanyId("1"))
                .willReturn(employees);
        //when`
        List<Employee> actual = companyService.findEmployeeById("1");
        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_get_all_companies_when_getByPaging_given_page_and_pageSize_and_company() throws Exception {
        //given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("1", "OOCL"));
        companies.add(new Company("2", "OOCL2"));

        Integer page = 1;
        Integer pageSize = 2;

        given(companyRepository.findAll(PageRequest.of(page, pageSize)))
                .willReturn(new PageImpl<>(companies, PageRequest.of(page,pageSize),pageSize));

        //when`
        List<Company> actual = companyService.findByPage(page, pageSize);
        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_return_company_when_perform_post_given_company() throws Exception {
        //given
        Company newCompany = new Company("3", "OOCL3");
        given(companyRepository.save(newCompany))
                .willReturn(newCompany);
        //when
        Company actual = companyService.create(newCompany);
        //then
        assertEquals(newCompany, actual);
    }

    @Test
    void should_return_update_company_when_perform_put_given_company_id() throws Exception {
        //given
        Company updatedCompany = new Company("1", "OOCLL");
        given(companyRepository.save(updatedCompany))
                .willReturn(updatedCompany);
        given(companyRepository.findById("1"))
                .willReturn(java.util.Optional.of(updatedCompany));
        //when
        Company actual = companyService.save("1", updatedCompany);
        //then
        assertEquals(updatedCompany, actual);
        assertEquals(updatedCompany.getName(), actual.getName());

    }

    @Test
    void should_delete_company_when_perform_delete_given_company_and_id() throws Exception {
        //given
        Company company = new Company("1", "OOCL");
        willDoNothing().given(companyRepository).deleteById(company.getId());
        //when
        companyService.delete(company.getId());
        //then
        verify(companyRepository).deleteById(company.getId());
    }

}
