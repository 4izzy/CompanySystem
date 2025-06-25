package com.company.hr.client;

import com.company.common.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url = "http://localhost:8082")
public interface EmployeeClient {
    @GetMapping("/api/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
} 