package com.imnu.dao;

import com.imnu.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId,@Param("blogParentId") long parseLong);

    Comment findByParentCommentId(@Param("parentCommentId") Long parentCommentId);

    int saveComment(Comment comment);
}
