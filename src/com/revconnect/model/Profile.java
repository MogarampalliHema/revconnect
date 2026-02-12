package com.revconnect.model;

public class Profile {

    private int profileId;
    private int userId;
    private String displayName;
    private String bio;
    private String address;
    private String contactInfo;
    private String website;
    private String profilePicturePath;

    // ===== GETTERS =====

    public int getProfileId() {
        return profileId;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getWebsite() {
        return website;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    // ===== SETTERS =====

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
