package com.revconnect.controller;

import com.revconnect.model.Interaction;
import com.revconnect.model.User;
import com.revconnect.service.InteractionService;
import com.revconnect.service.InteractionServiceImpl;

import java.util.List;
import java.util.Scanner;

public class InteractionController {

    private InteractionService interactionService =
            new InteractionServiceImpl();

    private Scanner sc = new Scanner(System.in);

    // 👍 LIKE POST (SAFE INPUT)
    public void likePost(User user) {
        try {
            System.out.print("Enter Post ID to like: ");
            int postId = Integer.parseInt(sc.nextLine());

            interactionService.likePost(postId, user.getUserId());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Post ID. Please enter a number.");
        }
    }

    // 💬 COMMENT ON POST (SAFE INPUT)
    public void commentOnPost(User user) {
        try {
            System.out.print("Enter Post ID to comment: ");
            int postId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter comment: ");
            String comment = sc.nextLine();

            Interaction interaction = new Interaction();
            interaction.setPostId(postId);
            interaction.setUserId(user.getUserId());
            interaction.setCommentText(comment);

            interactionService.commentOnPost(interaction);

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Post ID. Please enter a number.");
        }
    }

    // 👀 VIEW COMMENTS (SAFE INPUT)
    public void viewComments() {
        try {
            System.out.print("Enter Post ID to view comments: ");
            int postId = Integer.parseInt(sc.nextLine());

            List<Interaction> comments =
                    interactionService.viewComments(postId);

            System.out.println("\n--- Comments ---");
            if (comments.isEmpty()) {
                System.out.println("No comments yet.");
            }

            for (Interaction i : comments) {
                System.out.println(
                        "User " + i.getUserId() + ": " + i.getCommentText()
                );
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Post ID. Please enter a number.");
        }
    }
}
