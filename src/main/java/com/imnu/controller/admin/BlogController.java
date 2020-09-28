package com.imnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Blog;
import com.imnu.pojo.User;
import com.imnu.service.BlogService;
import com.imnu.service.TagService;
import com.imnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlogs = blogService.getAllBlog();
        PageInfo pageInfo = new PageInfo(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String searchBlogs(Blog blog,
                              @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                              Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> allblogs = blogService.searchAllBlog(blog);
        PageInfo pageInfo = new PageInfo(allblogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功！！");
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")         //新增博客界面
    public String toAddBlog(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")    //编辑博客界面
    public String toEditBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
        //设置user属性
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
            attributes.addFlashAttribute("msg", "新增成功！！");
        } else {
            attributes.addFlashAttribute("msg", "修改成功！！");
            blogService.updateBlog(blog);
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功！！！");
        return "redirect:/admin/blogs";
    }

}
