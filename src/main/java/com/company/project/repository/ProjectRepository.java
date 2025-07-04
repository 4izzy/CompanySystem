package com.company.project.repository;

import com.company.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(Project.ProjectStatus status);
    List<Project> findByEmployeesId(Long employeeId);
} 