package com.sametibis.employeeservice.service.impl;

import com.sametibis.employeeservice.dto.ApiResponseDto;
import com.sametibis.employeeservice.dto.DepartmentDto;
import com.sametibis.employeeservice.dto.EmployeeDto;
import com.sametibis.employeeservice.entity.Employee;
import com.sametibis.employeeservice.exception.ResourceAlreadyExistException;
import com.sametibis.employeeservice.exception.ResourceNotFoundException;
import com.sametibis.employeeservice.mapper.EmployeeMapper;
import com.sametibis.employeeservice.repository.EmployeeRepository;
import com.sametibis.employeeservice.service.ApiFeignClient;
import com.sametibis.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    // private final RestTemplate restTemplate;
    // private final WebClient webClient;
    private final ApiFeignClient apiFeignClient;



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
    public Optional<ApiResponseDto> getEmployeeByEmail(String email) {



        Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        DepartmentDto departmentDto = apiFeignClient.getDepartmentDto(employee.getDepartmentCode()) ;
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto);
        return Optional.of(apiResponseDto);
    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
    @Override
    public Optional<ApiResponseDto> getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found!") );

        // 1) RestTemplate
        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDto.class);
        // DepartmentDto departmentDto = responseEntity.getBody();

        // 2) WebClient
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block(); // since I use synchronous communication

        // 3) OpenFeign
        DepartmentDto departmentDto = apiFeignClient.getDepartmentDto(employee.getDepartmentCode()) ;

        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto);
        return Optional.of(apiResponseDto);
    }


    public Optional<ApiResponseDto> getDefaultDepartmentResponse(Long id, Exception exception) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found!") );
        DepartmentDto defaultDepartment = new DepartmentDto(9999L, "Default Department", "RD-01", "Default Department");
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, defaultDepartment);
        return Optional.of(apiResponseDto);
    }
}
