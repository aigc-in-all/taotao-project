package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        // TbItem item = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> items = itemMapper.selectByExample(example);
        if (items != null && items.size() > 0) {
            return items.get(0);
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<TbItem> info = new PageInfo<>(list);
        result.setTotal(info.getTotal());

        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item) {
        // item补全
        // 1.itemId
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // 2.商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        // 3.创建时间
        item.setCreated(new Date());
        // 4.更新时间
        item.setUpdated(new Date());

        // 插入数据库
        itemMapper.insert(item);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateItem(TbItem item) {
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKey(item);
        return TaotaoResult.ok();
    }

}
