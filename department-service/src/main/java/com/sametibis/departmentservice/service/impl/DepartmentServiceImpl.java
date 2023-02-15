package com.sametibis.departmentservice.service.impl;

import com.sametibis.departmentservice.dto.DepartmentDto;
import com.sametibis.departmentservice.entity.Department;
import com.sametibis.departmentservice.repository.DepartmentRepository;
import com.sametibis.departmentservice.service.DepartmentService;
import com.sametibis.departmentservice.exception.ResourceAlreadyExistException;
import com.sametibis.departmentservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import com.sametibis.departmentservice.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        if(departmentRepository.findDepartmentByDepartmentCode(departmentDto.getDepartmentCode()).isPresent()) {
            throw new ResourceAlreadyExistException("Department", "departmentCode", departmentDto.getDepartmentCode());
        }

        Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
        return savedDepartmentDto;

    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<DepartmentDto> getDepartmentByDepartmentCode(String departmentCode) {
        Department department = departmentRepository.findDepartmentByDepartmentCode(departmentCode).orElseThrow(() ->
                new ResourceNotFoundException("Department", "Department Code", departmentCode));
        DepartmentDto departmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(department);
        return Optional.of(departmentDto);
    }

    @Override
    public Department updateDepartmentById(Department department) {
        return null;
    }

    @Override
    public void deleteDepartmentById(Long id) {

    }
}
