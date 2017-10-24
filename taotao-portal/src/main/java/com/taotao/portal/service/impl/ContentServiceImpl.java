package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        String content = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        try {
            TaotaoResult result = TaotaoResult.formatToList(content, TbContent.class);
            List<TbContent> list = (List<TbContent>) result.getData();
            List<Map> resultList = new ArrayList<>();
            for (TbContent c : list) {
                Map map = new HashMap<>();
                map.put("src", c.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", c.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", c.getUrl());
                map.put("alt", c.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
