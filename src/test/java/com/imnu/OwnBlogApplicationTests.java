package com.imnu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Blog;
import com.imnu.pojo.Comment;
import com.imnu.pojo.Tag;
import com.imnu.pojo.Type;
import com.imnu.service.BlogService;
import com.imnu.service.CommentService;
import com.imnu.service.TagService;
import com.imnu.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OwnBlogApplicationTests {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {
    }

}
