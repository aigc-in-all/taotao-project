package com.taotao.portal.service;

import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
    ItemInfo getItemById(long itemId);

    String getDescById(long itemId);

    TbItemParamItem getParamById(long itemId);
}
