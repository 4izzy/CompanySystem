package com.company.project.service;

import com.company.employee.model.Employee;
import com.company.employee.service.EmployeeService;
import com.company.exception.ResourceNotFoundException;
import com.company.project.model.Project;
import com.company.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    }

    public List<Project> getProjectsByStatus(Project.ProjectStatus status) {
        return projectRepository.findByStatus(status);
    }

    public List<Project> getProjectsByEmployee(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return projectRepository.findByEmployeesId(employeeId);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setStatus(projectDetails.getStatus());
        project.setEmployees(projectDetails.getEmployees());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }

    public Project addEmployeeToProject(Long projectId, Long employeeId) {
        Project project = getProjectById(projectId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        project.getEmployees().add(employee);
        return projectRepository.save(project);
    }

    public Project removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = getProjectById(projectId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        project.getEmployees().remove(employee);
        return projectRepository.save(project);
    }
} 