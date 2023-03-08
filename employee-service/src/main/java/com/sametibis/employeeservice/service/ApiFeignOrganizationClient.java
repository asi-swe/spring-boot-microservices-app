package com.sametibis.employeeservice.service;

import com.sametibis.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface ApiFeignOrganizationClient {
    @GetMapping("/api/organizations/{organizationCode}")
    OrganizationDto getOrganizationByCode(@PathVariable String organizationCode);
}
