package com.sametibis.organizationservice.repository;

import com.sametibis.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findOrganizationByOrganizationCode(String organizationCode);
}
