package com.example.restapi.Employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository mockEmployeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>(Collections.singletonList(new Employee(1, "Marcus", 19, "Male", 1920213)));
        given(mockEmployeeRepository.findAll())
                .willReturn(employees);
        //when
        List<Employee> actual = employeeService.findAll();
        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_employee_when_getById_given_employees() {
        //given
        Employee employee = new Employee(1, "Marcus", 19, "Male", 1920213);

        given(mockEmployeeRepository.findById(employee.getId()))
                .willReturn(employee);
        //when
        Employee actualEmployee = employeeService.findById(employee.getId());
        //then
        assertEquals(employee, actualEmployee);
    }

    @Test
    void should_return_employees_when_getByGender_given_employees_and_gender() {
        //given
        List<Employee> employees = new ArrayList<>(Collections.singletonList(new Employee(1, "Marcus", 19, "Male", 1920213)));

        given(mockEmployeeRepository.findByGender(employees.get(0).getGender()))
                .willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.findByGender(employees.get(0).getGender());
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    void should_return_employees_when_getByPage_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "Marcus", 19, "Male", 1920213), new Employee(2, "Gloria", 19, "Female", 10000), new Employee(3, "Marcus2", 19, "Male", 1920213)));

        Integer page = 1;
        Integer pageSize = 2;
        given(mockEmployeeRepository.findByPage(page, pageSize))
                .willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.findByPage(page, pageSize);
        //then
        assertEquals(employees, actualEmployees);
    }


    @Test
    void should_return_updated_employee_when_edit_employee_given_updated_employee() {
        //given
        Employee employee = new Employee(1, "Marcus", 19, "Male", 1920213);
        Employee updatedEmployee = new Employee(1, "Marcus", 25, "Male", 9999999);
        given(mockEmployeeRepository.findById(any()))
                .willReturn(employee);
        employee.setAge(updatedEmployee.getAge());
        employee.setSalary(updatedEmployee.getSalary());
        given(mockEmployeeRepository.save(any(), any(Employee.class)))
                .willReturn(employee);

        //when
        Employee actual = employeeService.edit(employee.getId(), updatedEmployee);

        //then
        verify(mockEmployeeRepository).save(employee.getId(), employee);
        assertEquals(employee, actual);
    }

}
