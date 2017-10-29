package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {

    /**
     * 商品基本信息
     *
     * @param itemId
     * @return
     */
    TaotaoResult getItemBaseInfo(long itemId);

    /**
     * 商品描述
     *
     * @param itemId
     * @return
     */
    TaotaoResult getItemDesc(long itemId);

    /**
     * 商品规格
     *
     * @param itemId
     * @return
     */
    TaotaoResult getItemParam(long itemId);
}
