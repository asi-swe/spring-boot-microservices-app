package com.sametibis.departmentservice.service;

import com.sametibis.departmentservice.dto.DepartmentDto;
import com.sametibis.departmentservice.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<Department> getAllDepartments();
    Optional<DepartmentDto> getDepartmentByDepartmentCode(String departmentCode);
    Department updateDepartmentById(Department department);
    void deleteDepartmentById(Long id);


}
