package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {

    SearchResult search(String query, int page, int rows) throws Exception;
}
