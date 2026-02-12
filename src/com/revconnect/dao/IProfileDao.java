package com.revconnect.dao;

import com.revconnect.model.Profile;

public interface IProfileDao {

    void createProfile(Profile profile);
    Profile getProfileByUserId(int userId);
    void updateProfile(Profile profile);
}
