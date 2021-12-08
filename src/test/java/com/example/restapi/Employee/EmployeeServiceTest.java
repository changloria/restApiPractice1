package com.example.restapi.Employee;

import com.example.restapi.Employee.EmployeeRepository;
import com.example.restapi.Employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    @Spy
    EmployeeService employeeService;

    @Test
    void should_return_all_employees_when_find_all_given_employees() {
        //given
        List<Employee> employees = new ArrayList<>(Collections.singletonList(new Employee(1, "Marcus", 19, "Male", 1920213)));
        given(employeeRepository.findAll())
                .willReturn(employees);
        //when
        List<Employee> actual = employeeService.findAll();
        //then
        assertEquals(employees, actual);
    }

}
