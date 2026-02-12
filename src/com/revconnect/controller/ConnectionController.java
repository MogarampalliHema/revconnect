package com.revconnect.controller;

import com.revconnect.model.Connection;
import com.revconnect.model.User;
import com.revconnect.service.*;

import java.util.List;
import java.util.Scanner;

public class ConnectionController {

    private IConnectionService connectionService =
            new ConnectionServiceImpl();

    private Scanner sc = new Scanner(System.in);

    // 1️⃣ SEND CONNECTION REQUEST
    public void sendRequest(User user) {

        System.out.println("DEBUG → Logged-in User ID: " + user.getUserId());

        System.out.print("Enter User ID to connect: ");
        int targetId = Integer.parseInt(sc.nextLine());

        if (user.getUserId() == targetId) {
            System.out.println("❌ You cannot connect with yourself");
            return;
        }

        connectionService.sendRequest(
                user.getUserId(),
                targetId
        );

        System.out.println("🤝 Connection request sent");
    }

    // 2️⃣ VIEW PENDING REQUESTS (ONLY FOR TARGET USER)
    public void viewPending(User user) {

        System.out.println("DEBUG → Logged-in User ID: " + user.getUserId());

        List<Connection> list =
                connectionService.viewPending(user.getUserId());

        System.out.println("\n--- Pending Requests ---");

        if (list.isEmpty()) {
            System.out.println("No pending connection requests.");
            return;
        }

        for (Connection c : list) {
            System.out.println(
                    "Connection ID: " + c.getConnectionId() +
                            " | From User ID: " + c.getRequesterId()
            );
        }
    }

    // 3️⃣ ACCEPT CONNECTION REQUEST
    public void acceptRequest() {

        System.out.print("Enter Connection ID to accept: ");
        int connectionId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Requester User ID: ");
        int requesterId = Integer.parseInt(sc.nextLine());

        connectionService.acceptRequest(connectionId, requesterId);

        System.out.println("✅ Connection accepted");
    }

    // 4️⃣ VIEW ACCEPTED CONNECTIONS
    public void viewConnections(User user) {

        System.out.println("DEBUG → Logged-in User ID: " + user.getUserId());

        List<Connection> list =
                connectionService.viewConnections(user.getUserId());

        System.out.println("\n--- Your Connections ---");

        if (list.isEmpty()) {
            System.out.println("You have no connections yet.");
            return;
        }

        for (Connection c : list) {

            int otherUser =
                    (c.getRequesterId() == user.getUserId())
                            ? c.getTargetUserId()
                            : c.getRequesterId();

            System.out.println("Connected with User ID: " + otherUser);
        }
    }
}
