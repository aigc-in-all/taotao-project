package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(Long categoryId) {
        // 检查缓存
        try {
            String str = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(categoryId));
            if (StringUtil.isNotEmpty(str)) {
                List<TbContent> contents = JsonUtils.jsonToList(str, TbContent.class);
                return contents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = contentMapper.selectByExample(example);

        // 写入缓存
        try {
            String str = JsonUtils.objectToJson(contents);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(categoryId), str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contents;
    }

}
