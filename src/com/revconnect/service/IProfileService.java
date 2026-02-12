package com.revconnect.service;

import com.revconnect.model.Profile;

public interface IProfileService {

    void createProfile(Profile profile);
    Profile viewProfile(int userId);
    void updateProfile(Profile profile);
}
