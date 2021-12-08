package com.example.restapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void cleanRepository() {
        employeeRepository.cleaarAll();
    }


    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee(1, "Marcus", 19, "Male", 1920213);
        Employee employee2 = new Employee(1, "Gloria", 22, "Female", 1000000);
        employeeRepository.create(employee);
        employeeRepository.create(employee2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Marcus"))
                .andExpect(jsonPath("$[0].age").value("19"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value("1920213"));
    }

    @Test
    void should_get_employee_when_perform_getID_given_employee_and_id() throws Exception {
        //given
        Employee employee = new Employee(1, "Gloria", 22, "female", 1000000);
        employeeRepository.create(employee);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Gloria"))
                .andExpect(jsonPath("$.age").value("22"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("1000000"));
    }

    @Test
    void should_get_all_employees_when_perform_getByGender_given_employees_and_gender() throws Exception {
        //given
        Employee employee = new Employee(1, "Marcus", 19, "Male", 1920213);
        Employee employee2 = new Employee(1, "Gloria", 22, "Female", 1000000);

        employeeRepository.create(employee);
        employeeRepository.create(employee2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", employee.getGender()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Marcus"))
                .andExpect(jsonPath("$[0].age").value("19"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value("1920213"));
    }
}
