package com.revconnect.service;

import com.revconnect.dao.*;
import com.revconnect.model.Profile;

public class ProfileServiceImpl implements IProfileService {

    private IProfileDao profileDao = new ProfileDaoImpl();

    @Override
    public void createProfile(Profile profile) {
        profileDao.createProfile(profile);
    }

    @Override
    public Profile viewProfile(int userId) {
        return profileDao.getProfileByUserId(userId);
    }

    @Override
    public void updateProfile(Profile profile) {
        profileDao.updateProfile(profile);
    }
}
