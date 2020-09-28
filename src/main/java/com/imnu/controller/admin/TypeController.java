package com.imnu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imnu.pojo.Type;
import com.imnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                        Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Type> allTypes = typeService.getAllType();
        PageInfo<Type> typePageInfo = new PageInfo<>(allTypes);
        model.addAttribute("pageInfo", typePageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddType(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")  //新增
    public String addType(Type type, RedirectAttributes attributes) {
        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "不能添加重复的分类！！");
            return "redirect:/admin/types/input";
        } else {
            typeService.saveType(type);
            attributes.addFlashAttribute("msg", "增加成功");
            return "redirect:/admin/types";
        }
    }

    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id, Type type, RedirectAttributes attributes) {
        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "不能修改重复的分类！！");
            return "redirect:/admin/types/input";
        } else {
            typeService.updateType(type);
            attributes.addFlashAttribute("msg", "修改成功");
            return "redirect:/admin/types";
        }
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }


}
