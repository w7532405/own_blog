package com.imnu.service;

import com.imnu.pojo.Type;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeService {

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    List<Type> getAllType();

    Type getType(Long id);

    Type getTypeByName(String name);

    int saveType(Type type);

    int updateType(Type type);

    int deleteType(Long id);
}
