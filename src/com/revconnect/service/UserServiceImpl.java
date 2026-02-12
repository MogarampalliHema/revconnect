package com.revconnect.service;

import com.revconnect.dao.IUserDao;
import com.revconnect.dao.UserDaoImpl;
import com.revconnect.model.User;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    public UserServiceImpl(UserDaoImpl userDao) {
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }
}

