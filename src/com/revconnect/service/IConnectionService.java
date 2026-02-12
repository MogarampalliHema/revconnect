package com.revconnect.service;

import com.revconnect.model.Connection;
import java.util.List;

public interface IConnectionService {

    void sendRequest(int requesterId, int targetUserId);
    void acceptRequest(int connectionId, int requesterId);
    List<Connection> viewPending(int userId);
    List<Connection> viewConnections(int userId);
}
