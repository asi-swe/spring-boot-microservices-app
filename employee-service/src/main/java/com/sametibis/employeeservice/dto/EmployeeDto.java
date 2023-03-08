package com.sametibis.employeeservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email")
    @NotNull(message = "Email can not be null!")
    private String email;
    @NotNull(message = "Department code can not be null!")
    private String departmentCode;
    private String organizationCode;
}
