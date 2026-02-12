package com.revconnect.controller;

import com.revconnect.service.PostAnalyticsService;
import com.revconnect.service.PostAnalyticsServiceImpl;

import java.util.Scanner;

public class PostAnalyticsController {

    private PostAnalyticsService analyticsService =
            new PostAnalyticsServiceImpl();

    private Scanner sc = new Scanner(System.in);

    public void viewAnalytics() {

        System.out.print("Enter Post ID to view analytics: ");
        int postId = Integer.parseInt(sc.nextLine());

        analyticsService.viewAnalytics(postId);
    }
}
