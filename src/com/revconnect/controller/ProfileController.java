package com.revconnect.controller;

import com.revconnect.model.Profile;
import com.revconnect.model.User;
import com.revconnect.service.*;

import java.util.Scanner;

public class ProfileController {

    private IProfileService profileService =
            new ProfileServiceImpl();

    private Scanner sc = new Scanner(System.in);

    public void createOrUpdateProfile(User user) {

        Profile existing =
                profileService.viewProfile(user.getUserId());

        Profile p = new Profile();
        p.setUserId(user.getUserId());

        System.out.print("Display Name: ");
        p.setDisplayName(sc.nextLine());

        System.out.print("Bio: ");
        p.setBio(sc.nextLine());

        System.out.print("Address: ");
        p.setAddress(sc.nextLine());

        System.out.print("Contact Info: ");
        p.setContactInfo(sc.nextLine());

        System.out.print("Website: ");
        p.setWebsite(sc.nextLine());

        System.out.print("Profile Picture Path: ");
        p.setProfilePicturePath(sc.nextLine());

        if (existing == null) {
            profileService.createProfile(p);
        } else {
            profileService.updateProfile(p);
        }
    }

    public void viewProfile(User user) {

        Profile p =
                profileService.viewProfile(user.getUserId());

        if (p == null) {
            System.out.println("❌ Profile not created yet");
            return;
        }

        System.out.println("\n--- Profile ---");
        System.out.println("Name: " + p.getDisplayName());
        System.out.println("Bio: " + p.getBio());
        System.out.println("Address: " + p.getAddress());
        System.out.println("Contact: " + p.getContactInfo());
        System.out.println("Website: " + p.getWebsite());
        System.out.println("Profile Pic: " + p.getProfilePicturePath());
    }
}

