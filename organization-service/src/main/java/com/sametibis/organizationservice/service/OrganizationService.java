package com.sametibis.organizationservice.service;


import com.sametibis.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto addOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);

}
