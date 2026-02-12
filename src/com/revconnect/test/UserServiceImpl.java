package com.revconnect.test;

import com.revconnect.dao.IUserDao;
import com.revconnect.model.User;

public class UserServiceImpl {

    private IUserDao userDao;

    //  constructor injection (Mockito-friendly)
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }
}