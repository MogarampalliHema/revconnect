package com.revconnect.dao;

import com.revconnect.model.Notification;
import java.util.List;

public interface INotificationDao {

    void createNotification(
            int userId,
            String type,
            int referenceId,
            String message
    );

    List<Notification> getNotifications(int userId);

    void markAsRead(int notificationId);
}
