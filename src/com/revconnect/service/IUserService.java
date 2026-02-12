package com.revconnect.service;

import com.revconnect.model.User;

public interface IUserService {
    void register(User user);
    User login(String username, String password);
}

