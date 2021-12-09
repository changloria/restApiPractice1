package com.example.restapi.controller;

import com.example.restapi.dto.EmployeeRequest;
import com.example.restapi.dto.EmployeeResponse;
import com.example.restapi.entity.Employee;
import com.example.restapi.mapper.EmployeeMapper;
import com.example.restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable String id) {
        return employeeMapper.toResponse(employeeService.findById(id));
    }

    @GetMapping(params = {"gender"})
    public EmployeeResponse getAllEmployeesByGender(@RequestParam String gender) {
        return employeeMapper.toResponse((Employee) employeeService.findByGender(gender));
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllEmployeesByPage(@RequestParam Integer page, Integer pageSize) {
        return employeeService.findByPage(page, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.create(employeeMapper.toEntity(employeeRequest));
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.edit(id, employeeMapper.toEntity(employeeRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.delete(id);
    }
}
