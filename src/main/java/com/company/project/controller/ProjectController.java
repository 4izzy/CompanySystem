package com.company.project.controller;

import com.company.project.model.Project;
import com.company.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/status/{status}")
    public List<Project> getProjectsByStatus(@PathVariable Project.ProjectStatus status) {
        return projectService.getProjectsByStatus(status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Project> getProjectsByEmployee(@PathVariable Long employeeId) {
        return projectService.getProjectsByEmployee(employeeId);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{projectId}/employees/{employeeId}")
    public Project addEmployeeToProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        return projectService.addEmployeeToProject(projectId, employeeId);
    }

    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public Project removeEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        return projectService.removeEmployeeFromProject(projectId, employeeId);
    }
} 