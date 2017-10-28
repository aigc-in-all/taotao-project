package com.taotao.search.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String query, int page, int rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询参数
        if (StringUtils.isEmpty(query)) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.setQuery(query);
        }

        if (page == 0) {
            page = 1;
        }
        // 设置分页
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        // 设置默认搜索域
        solrQuery.set("df", "item_keywords");
        // 设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        // 执行查询
        SearchResult result = searchDao.search(solrQuery);

        long pageCount = result.getRecordCount() / rows;
        if (result.getRecordCount() % rows > 0) {
            pageCount++;
        }

        result.setPageCount(pageCount);
        result.setCurPage(page);
        return result;
    }

}
