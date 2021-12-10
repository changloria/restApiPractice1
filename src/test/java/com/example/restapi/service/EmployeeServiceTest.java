package com.example.restapi.service;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>(Collections.singletonList(new Employee("Marcus", 19, "Male", 1920213, "1")));
        given(employeeRepository.findAll())
                .willReturn(employees);
        //when
        List<Employee> actual = employeeService.findAll();
        //then
        assertEquals(employees, actual);
        assertEquals(employees.get(0).getName(), actual.get(0).getName());
    }

    @Test
    void should_return_employee_when_getById_given_employees() {
        //given
        Employee employee = new Employee("Marcus", 19, "Male", 1920213, "1");

        given(employeeRepository.findById("1"))
                .willReturn(java.util.Optional.of(employee));
        //when
        Employee actualEmployee = employeeService.findById("1");
        //then
        assertEquals(employee, actualEmployee);
    }

    @Test
    void should_return_employees_when_getByGender_given_employees_and_gender() {
        //given
        List<Employee> employees = new ArrayList<>(Collections.singletonList(new Employee("Marcus", 19, "Male", 1920213, "1")));

        given(employeeRepository.findAllByGender("Male"))
                .willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.findByGender("Male");
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    void should_return_employees_when_getByPage_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee( "Marcus", 19, "Male", 1920213, "1"), new Employee("Gloria", 19, "Female", 10000, "1"), new Employee("Lily", 19, "Female", 30000, "1")));

        Pageable pageable = PageRequest.of(1,1);
        given(employeeRepository.findAll(pageable))
                .willReturn(new PageImpl<>(employees, PageRequest.of(1, 1), 1));


        //when
        List<Employee> actualEmployees = employeeService.findByPage(1,1);
        //then
        assertEquals("Marcus", actualEmployees.get(0).getName());
        assertEquals(19, actualEmployees.get(0).getAge());
    }


    @Test
    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
        //given
        Employee employee = new Employee("Marcus", 19, "Male", 20000,"1");
        Employee updatedEmployee = new Employee("Marcus", 20, "Male", 26000, "1");
        given(employeeRepository.findById(any()))
                .willReturn(java.util.Optional.of(employee));
        employee.setAge(updatedEmployee.getAge());
        employee.setSalary(updatedEmployee.getSalary());
        given(employeeRepository.save(any(Employee.class)))
                .willReturn(employee);

        //when
        Employee actual = employeeService.edit(employee.getId(), updatedEmployee);

        //then
        verify(employeeRepository).save(employee);
        assertEquals(employee, actual);
    }

    @Test
    void should_return_created_employee_when_add_employee_given_new_employee() {
        //given
        Employee newEmployee = new Employee("Marcus", 25, "Male", 9999999, "1");
        given(employeeRepository.insert(newEmployee))
                .willReturn(newEmployee);

        //when
        Employee actual = employeeService.create(newEmployee);

        //then
        verify(employeeRepository).insert(newEmployee);
        assertEquals(newEmployee, actual);
    }

    @Test
    void should_return_nothing_when_delete_given_id_employee() {
        //given
        Employee employee = new Employee("Marcus", 25, "Male", 9999999, "1");

        willDoNothing().given(employeeRepository).deleteById(employee.getId());
        //when
        employeeService.delete(employee.getId());
        //then
        verify(employeeRepository).deleteById(employee.getId());
    }
}
