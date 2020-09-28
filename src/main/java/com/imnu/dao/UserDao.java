package com.imnu.dao;

import com.imnu.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;

@Mapper
public interface UserDao {
    User checkUser(@PathParam("username") String username, @PathParam("password") String password);
}
