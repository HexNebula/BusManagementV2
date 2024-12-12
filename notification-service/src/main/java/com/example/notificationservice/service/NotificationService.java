package com.example.notificationservice.service;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification saveNotification(String type, String details) {
        Notification notification = new Notification(type, details);
        return notificationRepository.save(notification);
    }
}