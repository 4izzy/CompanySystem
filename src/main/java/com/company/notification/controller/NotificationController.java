package com.company.notification.controller;

import com.company.notification.model.Notification;
import com.company.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/email/{email}")
    public List<Notification> getNotificationsByEmail(@PathVariable String email) {
        return notificationService.getNotificationsByEmail(email);
    }

    @GetMapping("/status/{status}")
    public List<Notification> getNotificationsByStatus(@PathVariable Notification.NotificationStatus status) {
        return notificationService.getNotificationsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Notification> getNotificationsByType(@PathVariable Notification.NotificationType type) {
        return notificationService.getNotificationsByType(type);
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PutMapping("/{id}/status")
    public Notification updateStatus(@PathVariable Long id, @RequestParam Notification.NotificationStatus status) {
        return notificationService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
} 