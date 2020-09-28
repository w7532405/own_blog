package com.imnu.service;

import com.imnu.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
