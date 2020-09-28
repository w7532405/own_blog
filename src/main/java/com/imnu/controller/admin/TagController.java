package com.imnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Tag;
import com.imnu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                       Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAddTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "不能添加重复的标签!!!");
            return "redirect:/admin/tags/input";
        } else {
            tagService.saveTag(tag);
            attributes.addFlashAttribute("msg", "增加成功!!!");
            return "redirect:/admin/tags";
        }
    }

    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes) {
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "不能修改重复的标签！！！");
            return "redirect:/admin/tags/input";
        } else {
            tagService.updateTag(tag);
            attributes.addFlashAttribute("msg", "修改成功！！！");
            return "redirect:/admin/tags";
        }
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", "删除成功！！！");
        return "redirect:/admin/tags";
    }
}
