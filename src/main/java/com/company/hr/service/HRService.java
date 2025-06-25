package com.company.hr.service;

import com.company.common.dto.NotificationDTO;
import com.company.hr.client.EmployeeClient;
import com.company.hr.client.NotificationClient;
import com.company.hr.model.EmployeeAction;
import com.company.hr.repository.EmployeeActionRepository;
import com.company.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HRService {
    private final EmployeeActionRepository employeeActionRepository;
    private final EmployeeClient employeeClient;
    private final NotificationClient notificationClient;

    public List<EmployeeAction> getAllActions() {
        return employeeActionRepository.findAll();
    }

    public EmployeeAction getActionById(Long id) {
        return employeeActionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAction", "id", id));
    }

    public List<EmployeeAction> getActionsByEmployee(Long employeeId) {
        return employeeActionRepository.findByEmployeeId(employeeId);
    }

    public List<EmployeeAction> getActionsByStatus(EmployeeAction.ActionStatus status) {
        return employeeActionRepository.findByStatus(status);
    }

    public List<EmployeeAction> getActionsByType(EmployeeAction.ActionType type) {
        return employeeActionRepository.findByType(type);
    }

    public EmployeeAction createAction(EmployeeAction action) {
        action.setStatus(EmployeeAction.ActionStatus.PENDING);
        EmployeeAction savedAction = employeeActionRepository.save(action);
        
        // Отправляем уведомление
        NotificationDTO notification = new NotificationDTO();
        notification.setRecipientEmail(employeeClient.getEmployeeById(action.getEmployeeId()).getEmail());
        notification.setSubject("New HR Action: " + action.getType());
        notification.setMessage("A new HR action has been created: " + action.getDescription());
        notification.setType(NotificationDTO.NotificationType.EMAIL);
        notificationClient.sendNotification(notification);
        
        return savedAction;
    }

    public EmployeeAction updateActionStatus(Long id, EmployeeAction.ActionStatus status, String comments) {
        EmployeeAction action = getActionById(id);
        action.setStatus(status);
        action.setComments(comments);
        EmployeeAction updatedAction = employeeActionRepository.save(action);
        
        // Отправляем уведомление об изменении статуса
        NotificationDTO notification = new NotificationDTO();
        notification.setRecipientEmail(employeeClient.getEmployeeById(action.getEmployeeId()).getEmail());
        notification.setSubject("HR Action Status Updated: " + action.getType());
        notification.setMessage("Your HR action status has been updated to: " + status + 
                             (comments != null ? "\nComments: " + comments : ""));
        notification.setType(NotificationDTO.NotificationType.EMAIL);
        notificationClient.sendNotification(notification);
        
        return updatedAction;
    }

    public void deleteAction(Long id) {
        EmployeeAction action = getActionById(id);
        employeeActionRepository.delete(action);
    }
} 