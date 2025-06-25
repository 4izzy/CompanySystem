package com.company.common.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private Double salary;
    private Long departmentId;
} 