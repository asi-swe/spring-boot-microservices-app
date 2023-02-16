package com.sametibis.employeeservice.controller;

import com.sametibis.employeeservice.dto.EmployeeDto;
import com.sametibis.employeeservice.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @PostMapping("add-employee")
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/{employee-email}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employee-email") String email) {
        EmployeeDto employeeDto = employeeService.getEmployeeByEmail(email).get();
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
