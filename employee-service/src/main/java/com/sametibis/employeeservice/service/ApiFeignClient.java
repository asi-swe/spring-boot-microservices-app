package com.sametibis.employeeservice.service;

import com.sametibis.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiFeignClient {
    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartmentDto(@PathVariable("department-code") String departmentCode);

}



