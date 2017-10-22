package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService catService;

    @RequestMapping("/list")
    @ResponseBody
    // 如果id为null是使用默认值，也就是parentid为0的分类列表
    public List<?> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<Map<String, String>> result = new ArrayList<>();
        List<TbItemCat> items = catService.getItemCatList(parentId);
        for (TbItemCat cat : items) {
            Map<String, String> node = new HashMap<>();
            node.put("id", String.valueOf(cat.getId()));
            node.put("text", cat.getName());
            node.put("state", cat.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        return result;
    }
}
