package com.sametibis.organizationservice.controller;

import com.sametibis.organizationservice.dto.OrganizationDto;
import com.sametibis.organizationservice.service.impl.OrganizationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    @PostMapping("/add-organization")
    ResponseEntity<OrganizationDto> addOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganizationDto = organizationService.addOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{organizationCode}")
    ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String organizationCode) {
        return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode), HttpStatus.OK);
    }
}
