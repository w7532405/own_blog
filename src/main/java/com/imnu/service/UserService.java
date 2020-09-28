package com.imnu.service;

import com.imnu.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User checkUser(String username, String password);
}
