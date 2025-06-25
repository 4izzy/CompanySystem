package com.company.hr.repository;

import com.company.hr.model.EmployeeAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeActionRepository extends JpaRepository<EmployeeAction, Long> {
    List<EmployeeAction> findByEmployeeId(Long employeeId);
    List<EmployeeAction> findByStatus(EmployeeAction.ActionStatus status);
    List<EmployeeAction> findByType(EmployeeAction.ActionType type);
} 