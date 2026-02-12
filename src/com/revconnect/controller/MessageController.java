package com.revconnect.controller;

import com.revconnect.model.Message;
import com.revconnect.model.User;
import com.revconnect.service.*;

import java.util.List;
import java.util.Scanner;

public class MessageController {

    private IMessageService messageService =
            new MessageServiceImpl();

    private Scanner sc = new Scanner(System.in);

    public void sendMessage(User user) {

        System.out.print("Enter Receiver User ID: ");
        int receiverId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter message: ");
        String text = sc.nextLine();

        Message message = new Message();
        message.setSenderId(user.getUserId());
        message.setReceiverId(receiverId);
        message.setMessageText(text);

        messageService.sendMessage(message);
        System.out.println("📩 Message sent");
    }

    public void viewChat(User user) {

        System.out.print("Enter User ID to view chat: ");
        int otherUser = Integer.parseInt(sc.nextLine());

        List<Message> chat =
                messageService.viewChat(user.getUserId(), otherUser);

        System.out.println("\n--- Chat ---");

        if (chat.isEmpty()) {
            System.out.println("No messages yet.");
            return;
        }

        for (Message m : chat) {
            String who =
                    (m.getSenderId() == user.getUserId())
                            ? "You"
                            : "User " + m.getSenderId();

            System.out.println(who + ": " + m.getMessageText());
        }
    }
}
