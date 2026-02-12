package com.revconnect.dao;

import com.revconnect.model.Message;
import java.util.List;

public interface IMessageDao {

    void sendMessage(Message message);
    List<Message> getChat(int user1, int user2);
}
