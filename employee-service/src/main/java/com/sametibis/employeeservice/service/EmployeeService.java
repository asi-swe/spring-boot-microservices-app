package com.sametibis.employeeservice.service;

import com.sametibis.employeeservice.dto.EmployeeDto;
import com.sametibis.employeeservice.entity.Employee;

import java.util.Optional;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    Optional<EmployeeDto> getEmployeeByEmail(String email);
}
