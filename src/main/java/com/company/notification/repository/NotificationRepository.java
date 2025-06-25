package com.company.notification.repository;

import com.company.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findByRecipientEmail(String recipientEmail);
    
    List<Notification> findByStatus(Notification.NotificationStatus status);
    
    List<Notification> findByType(Notification.NotificationType type);
    
    List<Notification> findByRecipientEmailAndStatus(String email, Notification.NotificationStatus status);
} 