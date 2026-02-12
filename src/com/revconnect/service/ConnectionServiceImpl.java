package com.revconnect.service;

import com.revconnect.dao.*;
import com.revconnect.model.Connection;

import java.util.List;

public class ConnectionServiceImpl implements IConnectionService {

    private IConnectionDao connectionDao = new ConnectionDaoImpl();
    private INotificationDao notificationDao = new NotificationDaoImpl();

    @Override
    public void sendRequest(int requesterId, int targetUserId) {
        connectionDao.sendRequest(requesterId, targetUserId);
        notificationDao.createNotification(
                targetUserId,
                "CONNECTION_REQUEST",
                requesterId,
                "You received a connection request"
        );
    }

    @Override
    public void acceptRequest(int connectionId, int requesterId) {
        connectionDao.updateStatus(connectionId, "ACCEPTED");
        notificationDao.createNotification(
                requesterId,
                "CONNECTION_ACCEPTED",
                connectionId,
                "Your connection request was accepted"
        );
    }

    @Override
    public List<Connection> viewPending(int userId) {
        return connectionDao.getPendingRequests(userId);
    }

    @Override
    public List<Connection> viewConnections(int userId) {
        return connectionDao.getConnections(userId);
    }
}
