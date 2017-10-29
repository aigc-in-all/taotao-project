package com.taotao.portal.pojo;

import java.util.List;

public class SearchResult {

    private List<Item> items;
    private long recordCount;
    private long pageCount;
    private int curPage;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
