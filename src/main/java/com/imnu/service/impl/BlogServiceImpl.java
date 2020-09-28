package com.imnu.service.impl;

import com.imnu.dao.BlogDao;
import com.imnu.exception.NotFoundException;
import com.imnu.pojo.Blog;
import com.imnu.pojo.BlogAndTag;
import com.imnu.pojo.Tag;
import com.imnu.service.BlogService;
import com.imnu.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html

        return blog;
    }

    @Override
    public List<Blog> getByTypeId(Long id) {
        return blogDao.getByTypeId(id);
    }

    @Override
    public List<Blog> getByTagId(Long id) {
        return blogDao.getByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int coutBlog() {
        return blogDao.getAllBlog().size();
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogDao.searchAllBlog(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogDao.saveBlog(blog);
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }


}
