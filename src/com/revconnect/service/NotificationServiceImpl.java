package com.revconnect.service;

import com.revconnect.dao.*;
import com.revconnect.model.Notification;

import java.util.List;

public class NotificationServiceImpl implements INotificationService {

    private INotificationDao notificationDao =
            new NotificationDaoImpl();

    @Override
    public List<Notification> viewNotifications(int userId) {
        return notificationDao.getNotifications(userId);
    }

    @Override
    public void markAsRead(int notificationId) {
        notificationDao.markAsRead(notificationId);
    }
}
