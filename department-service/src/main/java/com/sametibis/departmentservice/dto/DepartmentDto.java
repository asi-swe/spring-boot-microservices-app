package com.sametibis.departmentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    @NotNull(message = "Department name is required!")
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
