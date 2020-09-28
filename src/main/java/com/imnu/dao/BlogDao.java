package com.imnu.dao;

import com.imnu.pojo.Blog;
import com.imnu.pojo.BlogAndTag;
import com.imnu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Mapper
//@Repository
public interface BlogDao {

    List<Blog> getIndexBlog();          //主页博客展示

    List<Blog> getAllRecommendBlog();   //推荐博客展示

    List<Blog> getSearchBlog(String query);         //全局搜索

    Blog getDetailedBlog(Long id);

    List<Blog> getByTypeId(Long id);

    List<Blog> getByTagId(Long id);

    List<String> findGroupYear();

    List<Blog> findByYear(@Param("year") String year);

    List<Blog> getAllBlog();

    List<Blog> searchAllBlog(Blog blog);

    Blog getBlog(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlog(Long id);
}
