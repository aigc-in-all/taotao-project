package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        List<EUTreeNode> result = new ArrayList<>();

        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = categoryMapper.selectByExample(example);
        for (TbContentCategory c : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(c.getId());
            node.setText(c.getName());
            node.setState(c.getIsParent() ? "closed" : "open");
            result.add(node);
        }

        return result;
    }

    @Override
    public TaotaoResult createCategory(Long parentId, String name) {
        TbContentCategory category = new TbContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        category.setStatus(1);
        category.setIsParent(false);
        category.setSortOrder(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        categoryMapper.insert(category);

        TbContentCategory c = categoryMapper.selectByPrimaryKey(parentId);
        if (!c.getIsParent()) {
            c.setIsParent(true);
            categoryMapper.updateByPrimaryKey(c);
        }
        return TaotaoResult.ok(category);
    }

    @Override
    public TaotaoResult deleteCategory(Long categoryId) {
        TbContentCategory parentCategory = categoryMapper.selectByPrimaryKey(categoryId);

        categoryMapper.deleteByPrimaryKey(categoryId);

        if (parentCategory != null) {
            TbContentCategoryExample example = new TbContentCategoryExample();
            Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentCategory.getId());
            List<TbContentCategory> list = categoryMapper.selectByExample(example);
            if (list.isEmpty()) {
                parentCategory.setStatus(0);
                categoryMapper.updateByPrimaryKey(parentCategory);
            }
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateCategory(Long id, String name) {
        TbContentCategory category = categoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        categoryMapper.updateByPrimaryKey(category);
        return TaotaoResult.ok();
    }

}
