package com.sametibis.organizationservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrganizationDto {
    private Long id;
    @NotNull(message = "Organization Name can not be null!")
    private String organizationName;
    private String organizationDescription;
    @NotNull(message = "Organization Code can not be null!")
    private String organizationCode;
    private LocalDateTime createdDate;
}
