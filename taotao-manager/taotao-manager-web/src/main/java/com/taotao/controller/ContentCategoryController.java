package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService categoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> result = categoryService.getCategoryList(parentId);
        return result;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId, String name) {
        TaotaoResult result = categoryService.createCategory(parentId, name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteCategory(Long id) {
        TaotaoResult result = categoryService.deleteCategory(id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateCategory(Long id, String name) {
        TaotaoResult result = categoryService.updateCategory(id, name);
        return result;
    }


}
