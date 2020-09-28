package com.imnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Blog;
import com.imnu.pojo.Tag;
import com.imnu.pojo.Type;
import com.imnu.service.BlogService;
import com.imnu.service.TagService;
import com.imnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @GetMapping("/")
    public String toIndex(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 8);
        List<Blog> allBlogs = blogService.getIndexBlog();
        List<Type> allTypes = typeService.getBlogType();
        List<Tag> allTags = tagService.getBlogTag();
        List<Blog> allRecommendBlogs = blogService.getAllRecommendBlog();

        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTags);
        model.addAttribute("types", allTypes);
        model.addAttribute("recommendBlogs", allRecommendBlogs);
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model) {
        PageInfo pageInfo = new PageInfo();
        model.addAttribute("pageInfo", pageInfo);
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                         @RequestParam String query,
                         Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> searchBlogs = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model) {
        Blog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

}
