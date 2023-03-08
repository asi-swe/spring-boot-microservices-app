package com.sametibis.organizationservice.mapper;

import com.sametibis.organizationservice.dto.OrganizationDto;
import com.sametibis.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    OrganizationMapper ORGANIZATION_MAPPER = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto mapToOrganizationDto(Organization organization);

    Organization mapToOrganization(OrganizationDto organizationDto);
}
