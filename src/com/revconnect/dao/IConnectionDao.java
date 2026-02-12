package com.revconnect.dao;

import com.revconnect.model.Connection;
import java.util.List;

public interface IConnectionDao {

    void sendRequest(int requesterId, int targetUserId);
    void updateStatus(int connectionId, String status);
    List<Connection> getPendingRequests(int userId);
    List<Connection> getConnections(int userId);
}
