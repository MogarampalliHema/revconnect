package com.revconnect.controller;

import com.revconnect.model.Post;
import com.revconnect.model.User;
import com.revconnect.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PostController {

    private IPostService postService = new PostServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void createPost(User user) {

        System.out.print("Enter post content: ");
        String content = sc.nextLine();

        System.out.print("Enter hashtags (comma separated): ");
        String input = sc.nextLine();

        List<String> hashtags = Arrays.asList(input.split(","));

        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setContent(content);
        post.setHashtags(hashtags);

        postService.createPost(post);
        System.out.println("✅ Post created with hashtags");
    }

    public void viewAllPosts() {
        postService.viewAllPosts();
    }
}
