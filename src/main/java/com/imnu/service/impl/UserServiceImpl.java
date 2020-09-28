package com.imnu.service.impl;

import com.imnu.dao.UserDao;
import com.imnu.pojo.User;
import com.imnu.service.UserService;
import com.imnu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, MD5Utils.code(password));
    }
}
