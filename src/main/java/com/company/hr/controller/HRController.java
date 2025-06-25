package com.company.hr.controller;

import com.company.hr.model.EmployeeAction;
import com.company.hr.service.HRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/actions")
@RequiredArgsConstructor
public class HRController {
    private final HRService hrService;

    @GetMapping
    public List<EmployeeAction> getAllActions() {
        return hrService.getAllActions();
    }

    @GetMapping("/{id}")
    public EmployeeAction getActionById(@PathVariable Long id) {
        return hrService.getActionById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeAction> getActionsByEmployee(@PathVariable Long employeeId) {
        return hrService.getActionsByEmployee(employeeId);
    }

    @GetMapping("/status/{status}")
    public List<EmployeeAction> getActionsByStatus(@PathVariable EmployeeAction.ActionStatus status) {
        return hrService.getActionsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<EmployeeAction> getActionsByType(@PathVariable EmployeeAction.ActionType type) {
        return hrService.getActionsByType(type);
    }

    @PostMapping
    public EmployeeAction createAction(@RequestBody EmployeeAction action) {
        return hrService.createAction(action);
    }

    @PutMapping("/{id}/status")
    public EmployeeAction updateActionStatus(@PathVariable Long id, @RequestParam EmployeeAction.ActionStatus status, @RequestParam(required = false) String comments) {
        return hrService.updateActionStatus(id, status, comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAction(@PathVariable Long id) {
        hrService.deleteAction(id);
        return ResponseEntity.ok().build();
    }
} 