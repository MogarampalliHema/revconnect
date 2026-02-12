package com.revconnect.service;

import com.revconnect.model.Notification;
import java.util.List;

public interface INotificationService {

    List<Notification> viewNotifications(int userId);
    void markAsRead(int notificationId);
}
