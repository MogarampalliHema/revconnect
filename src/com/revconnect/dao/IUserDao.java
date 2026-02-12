package com.revconnect.dao;

import com.revconnect.model.User;

public interface IUserDao {
    void register(User user);
    User login(String username, String password);


}
