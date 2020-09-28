package com.imnu.service;

import com.imnu.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> getIndexBlog();                      //主页博客展示

    List<Blog> getAllRecommendBlog();               //推荐博客展示

    List<Blog> getSearchBlog(String query);         //全局搜索博客

    Blog getDetailedBlog(Long id);

    List<Blog> getByTypeId(Long id);

    List<Blog> getByTagId(Long id);

    Map<String, List<Blog>> archiveBlog();

    int coutBlog();

    List<Blog> getAllBlog();

    List<Blog> searchAllBlog(Blog blog);

    Blog getBlog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);
}
