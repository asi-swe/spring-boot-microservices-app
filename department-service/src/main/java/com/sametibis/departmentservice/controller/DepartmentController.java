package com.sametibis.departmentservice.controller;

import com.sametibis.departmentservice.dto.DepartmentDto;
import com.sametibis.departmentservice.entity.Department;
import com.sametibis.departmentservice.service.impl.DepartmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/department"))
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartmentDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(createdDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByDepartmentCode(@PathVariable("department-code") String deparmentCode) {
        DepartmentDto departmentDto = departmentService.getDepartmentByDepartmentCode(deparmentCode).get();
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

}
