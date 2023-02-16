package com.sametibis.employeeservice.service.impl;

import com.sametibis.employeeservice.dto.EmployeeDto;
import com.sametibis.employeeservice.entity.Employee;
import com.sametibis.employeeservice.exception.ResourceAlreadyExistException;
import com.sametibis.employeeservice.exception.ResourceNotFoundException;
import com.sametibis.employeeservice.mapper.EmployeeMapper;
import com.sametibis.employeeservice.repository.EmployeeRepository;
import com.sametibis.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if(employeeRepository.findEmployeeByEmail(employeeDto.getEmail()).isPresent())
            throw new ResourceAlreadyExistException("Resource already exist with email: " + employeeDto.getEmail());

        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public Optional<EmployeeDto> getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        return Optional.of(employeeDto);
    }
}
