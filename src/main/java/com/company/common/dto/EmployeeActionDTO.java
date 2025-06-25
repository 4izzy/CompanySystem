package com.company.common.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmployeeActionDTO {
    private Long id;
    private Long employeeId;
    private ActionType type;
    private String description;
    private ActionStatus status;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum ActionType {
        TRANSFER, SALARY_INCREASE, TERMINATION, LEAVE_REQUEST
    }
    public enum ActionStatus {
        PENDING, APPROVED, REJECTED
    }
} 