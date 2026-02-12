package com.revconnect.service;

import com.revconnect.model.Message;
import java.util.List;

public interface IMessageService {

    void sendMessage(Message message);
    List<Message> viewChat(int user1, int user2);
}
