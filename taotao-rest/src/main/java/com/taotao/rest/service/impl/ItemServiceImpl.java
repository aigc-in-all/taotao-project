package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private int REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {
        // 检查缓存
        try {
            String str = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if (StringUtil.isNotEmpty(str)) {
                TbItem item = JsonUtils.jsonToPojo(str, TbItem.class);
                return TaotaoResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        // 写入缓存
        try {
            // 把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
            // 设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDesc(long itemId) {
        // 检查缓存
        try {
            String str = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (StringUtil.isNotEmpty(str)) {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(str, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        // 写入缓存
        try {
            // 把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
            // 设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParam(long itemId) {
        // 检查缓存
        try {
            String str = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (StringUtil.isNotEmpty(str)) {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(str, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemParamItem itemParamItem = itemParamMapper.selectByPrimaryKey(itemId);

        // 写入缓存
        try {
            // 把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem));
            // 设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(itemParamItem);
    }

}
