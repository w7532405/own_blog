package com.imnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Blog;
import com.imnu.pojo.Tag;
import com.imnu.service.BlogService;
import com.imnu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id,
                       @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                       Model model) {
        PageHelper.startPage(pagenum, 100);  //开启分页
        List<Tag> tags = tagService.getBlogTag();
        //-1从导航点过来的
        if (id == -1) {
            id = tags.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
