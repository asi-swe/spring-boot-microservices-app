package com.sametibis.organizationservice.service.impl;

import com.sametibis.organizationservice.dto.OrganizationDto;
import com.sametibis.organizationservice.entity.Organization;
import com.sametibis.organizationservice.exception.ResourceAlreadyExistException;
import com.sametibis.organizationservice.exception.ResourceNotFoundException;
import com.sametibis.organizationservice.mapper.OrganizationMapper;
import com.sametibis.organizationservice.repository.OrganizationRepository;
import com.sametibis.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto addOrganization(OrganizationDto organizationDto) {
        if(organizationRepository.findOrganizationByOrganizationCode(organizationDto.getOrganizationCode()).isPresent())
            throw new ResourceAlreadyExistException("Organization already exist with this code: "  + organizationDto.getOrganizationCode());

        Organization organization = OrganizationMapper.ORGANIZATION_MAPPER.ORGANIZATION_MAPPER.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.ORGANIZATION_MAPPER.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findOrganizationByOrganizationCode(organizationCode).orElseThrow(() -> new ResourceNotFoundException("Organization not found with code: " + organizationCode));
        return  OrganizationMapper.ORGANIZATION_MAPPER.mapToOrganizationDto(organization);
    }
}
