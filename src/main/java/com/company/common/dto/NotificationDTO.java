package com.company.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private String recipientEmail;
    private String subject;
    private String message;
    private NotificationType type;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum NotificationType {
        EMAIL, SMS, SYSTEM
    }
    public enum NotificationStatus {
        PENDING, SENT, FAILED
    }
} 