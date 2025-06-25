package com.company.hr.client;

import com.company.common.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8084")
public interface NotificationClient {
    @PostMapping("/api/notifications")
    NotificationDTO sendNotification(@RequestBody NotificationDTO notificationDTO);
} 