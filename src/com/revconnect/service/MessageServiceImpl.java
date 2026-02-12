package com.revconnect.service;

import com.revconnect.dao.*;
import com.revconnect.model.Message;

import java.util.List;

public class MessageServiceImpl implements IMessageService {

    private IMessageDao messageDao = new MessageDaoImpl();

    @Override
    public void sendMessage(Message message) {
        messageDao.sendMessage(message);
    }

    @Override
    public List<Message> viewChat(int user1, int user2) {
        return messageDao.getChat(user1, user2);
    }
}
