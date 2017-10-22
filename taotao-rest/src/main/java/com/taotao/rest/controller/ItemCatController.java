package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    //    @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    //    @ResponseBody
    //    public String getItemCatList(String callback) {
    //        CatResult result = itemCatService.getItemCatList();
    //        String str = JsonUtils.objectToJson(result);
    //        return callback + "(" + str + ")";
    //    }

    @RequestMapping(value = "/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult result = itemCatService.getItemCatList();
        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;
    }
}
