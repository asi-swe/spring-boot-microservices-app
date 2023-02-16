package com.sametibis.employeeservice.controller;

import com.sametibis.employeeservice.dto.ApiResponseDto;
import com.sametibis.employeeservice.dto.EmployeeDto;
import com.sametibis.employeeservice.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable("employee-id") Long id) {
        ApiResponseDto apiResponseDto = employeeService.getEmployeeById(id).get();
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }


    @GetMapping("/employee")
    public ResponseEntity<EmployeeDto> getEmployee(@RequestParam String email) {
        EmployeeDto employeeDto = employeeService.getEmployeeByEmail(email).get();
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
