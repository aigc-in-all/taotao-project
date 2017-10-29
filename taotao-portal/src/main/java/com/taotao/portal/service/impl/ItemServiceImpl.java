package com.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (StringUtils.isNotBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (result.getStatus() == 200) {
                    return (ItemInfo) result.getData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String getDescById(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            if (StringUtils.isNotBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
                if (result.getStatus() == 200) {
                    TbItemDesc desc = (TbItemDesc) result.getData();
                    return desc.getItemDesc();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TbItemParamItem getParamById(long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            if (StringUtils.isNotBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
                if (result.getStatus() == 200) {
                    return (TbItemParamItem) result.getData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
