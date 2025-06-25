package com.company.employee.service;

import com.company.department.model.Department;
import com.company.department.service.DepartmentService;
import com.company.employee.model.Employee;
import com.company.employee.repository.EmployeeRepository;
import com.company.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "email", email));
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Employee with email " + employee.getEmail() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
} 