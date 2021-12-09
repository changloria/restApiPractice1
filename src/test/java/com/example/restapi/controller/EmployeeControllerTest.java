package com.example.restapi.controller;

import com.example.restapi.entity.Employee;
import com.example.restapi.repository.EmployeeRepository;
import com.example.restapi.repository.EmployeeRepositoryNew;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepositoryNew employeeRepositoryNew;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void cleanRepository() {
        employeeRepositoryNew.deleteAll();
    }


    @Test
    void should_get_all_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee("1", "Marcus", 19, "Male", 1920213,"1");
        Employee employee2 = new Employee("2", "Gloria", 22, "Female", 1000000, "1");
        employeeRepositoryNew.insert(employee);
        employeeRepositoryNew.insert(employee2);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Marcus"))
                .andExpect(jsonPath("$[0].age").value("19"))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    void should_get_employee_when_perform_getID_given_employee_and_id() throws Exception {
        //given
        Employee employee = new Employee("1", "Gloria", 22, "female", 1000000, "1");
        employeeRepositoryNew.insert(employee);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Gloria"))
                .andExpect(jsonPath("$.age").value("22"))
                .andExpect(jsonPath("$.gender").value("female"));
    }

    ///Fail
    @Test
    void should_get_all_employees_when_perform_getByGender_given_employees_and_gender() throws Exception {
        //given
        Employee employee = new Employee("1", "Marcus", 19, "Male", 1920213,"1");
        Employee employee2 = new Employee("2", "Gloria", 22, "Female", 1000000,"1");

        employeeRepositoryNew.insert(employee);
        employeeRepositoryNew.insert(employee2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", employee.getGender()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Marcus"))
                .andExpect(jsonPath("$[0].age").value("19"))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    void should_get_employee_list_when_perform_getByPage_given_employees() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Marcus", 19, "Male", 1920213,"1" );
        Employee employee2 = new Employee("2", "Gloria", 22, "Female", 1000000,"1");
        Employee employee3 = new Employee("3", "Linne", 22, "Female", 1000000,"1");

        employeeRepositoryNew.insert(employee1);
        employeeRepositoryNew.insert(employee2);
        employeeRepositoryNew.insert(employee3);
        //when

        //then
        String pageNum = "0";
        String pageSize = "2";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?page="+pageNum+"&pageSize="+pageSize)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Marcus"))
                .andExpect(jsonPath("$[0].age").value("19"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Gloria"))
                .andExpect(jsonPath("$[1].age").value("22"))
                .andExpect(jsonPath("$[1].gender").value("Female"));
    }

    @Test
    void should_return_employee_when_perform_post_given_employee() throws Exception {
        //given
        String employee = "{\n" +
                "        \"name\": \"Marcus\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"salary\": 298912220\n" +
                "    }";

        //when
        //then
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Marcus"))
                .andExpect(jsonPath("$.age").value("22"))
                .andExpect(jsonPath("$.gender").value("Male"));

    }

    @Test
    void should_return_changed_employee_when_perform_put_given_employee_id() throws Exception {
        //given
        Employee employee1 = new Employee("1", "Marcus", 19, "Male", 1920213,"1");
        Employee employee2 = new Employee("2", "Gloria", 22, "Female", 1000000, "1");
        Employee employee3 = new Employee("3", "Linne", 22, "Female", 1000000,"1");

        employeeRepositoryNew.insert(employee1);
        employeeRepositoryNew.insert(employee2);
        employeeRepositoryNew.insert(employee3);

        String employee = "{\n" +
                "        \"age\": 25,\n" +
                "        \"salary\": 298919999\n" +
                "    }";

        //when
        //then
        mockMvc.perform(put("/employees/{id}", employee1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marcus"))
                .andExpect(jsonPath("$.age").value("25"))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void should_delete_employee_when_perform_delete_given_employee_and_id() throws Exception {
        //given
        Employee employee = new Employee("1", "Marcus", 19, "Male", 1920213,"1" );
        Employee employee2 = new Employee("2", "Gloria", 22, "Female", 1000000,"1");

        employeeRepositoryNew.insert(employee);
        employeeRepositoryNew.insert(employee2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employee.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
