package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);

    TaotaoResult createCategory(Long parentId, String name);

    TaotaoResult deleteCategory(Long categoryId);

    TaotaoResult updateCategory(Long parentId, String name);
}
