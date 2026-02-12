package com.revconnect.controller;

import com.revconnect.model.Notification;
import com.revconnect.model.User;
import com.revconnect.service.*;

import java.util.List;
import java.util.Scanner;

public class NotificationController {

    private INotificationService notificationService =
            new NotificationServiceImpl();

    private Scanner sc = new Scanner(System.in);

    public void viewNotifications(User user) {

        List<Notification> list =
                notificationService.viewNotifications(user.getUserId());

        System.out.println("\n--- Notifications ---");

        if (list.isEmpty()) {
            System.out.println("No notifications.");
            return;
        }

        for (Notification n : list) {
            System.out.println(
                    "ID: " + n.getNotificationId() +
                            " | " + n.getMessage() +
                            (n.getIsRead() == 0 ? " (UNREAD)" : "")
            );
        }
    }

    public void markAsRead() {
        System.out.print("Enter Notification ID to mark as read: ");
        int id = Integer.parseInt(sc.nextLine());

        notificationService.markAsRead(id);
        System.out.println("✅ Notification marked as read");
    }
}
