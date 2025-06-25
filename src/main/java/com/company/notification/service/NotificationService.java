package com.company.notification.service;

import com.company.common.dto.NotificationDTO;
import com.company.notification.model.Notification;
import com.company.notification.repository.NotificationRepository;
import com.company.exception.ResourceNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public Notification createNotification(Notification notification) {
        notification.setStatus(Notification.NotificationStatus.PENDING);
        Notification savedNotification = notificationRepository.save(notification);
        sendNotification(savedNotification);
        return savedNotification;
    }

    public List<Notification> getNotificationsByEmail(String email) {
        return notificationRepository.findByRecipientEmail(email);
    }

    public List<Notification> getNotificationsByStatus(Notification.NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

    public List<Notification> getNotificationsByType(Notification.NotificationType type) {
        return notificationRepository.findByType(type);
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
    }

    public Notification updateStatus(Long id, Notification.NotificationStatus status) {
        Notification notification = getNotificationById(id);
        notification.setStatus(status);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }

    private void sendNotification(Notification notification) {
        try {
            switch (notification.getType()) {
                case EMAIL:
                    sendEmail(notification);
                    break;
                case SMS:
                    // TODO: Implement SMS sending
                    log.warn("SMS sending not implemented yet");
                    break;
                case SYSTEM:
                    // TODO: Implement system notification
                    log.warn("System notification not implemented yet");
                    break;
            }
            notification.setStatus(Notification.NotificationStatus.SENT);
        } catch (Exception e) {
            log.error("Failed to send notification: {}", e.getMessage());
            notification.setStatus(Notification.NotificationStatus.FAILED);
            notification.setErrorMessage(e.getMessage());
            notification.setRetryCount(notification.getRetryCount() + 1);
        }
        notificationRepository.save(notification);
    }

    private void sendEmail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getRecipientEmail());
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());
        mailSender.send(message);
    }

    public Notification retryNotification(Long id) {
        Notification notification = getNotificationById(id);
        if (notification.getStatus() == Notification.NotificationStatus.FAILED) {
            sendNotification(notification);
        }
        return notification;
    }
} 