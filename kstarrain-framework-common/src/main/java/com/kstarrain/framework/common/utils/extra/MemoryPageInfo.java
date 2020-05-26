package com.kstarrain.framework.common.utils.extra;

import lombok.Data;

@Data
public class MemoryPageInfo<T> {

    //当前页
    private int pageNum;
    //每页显示条数
    private int pageSize;
    //总页数
    private int pages = 0;
    //上一页
    private int prePage;
    //下一页
    private int nextPage;
    //总条数
    private long total = 0;

    // 是否为首页
    private boolean isFirstPage;
    // 是否为末页
    private boolean isLastPage;
    // 是否有上一页
    private boolean hasPreviousPage;
    // 是否有下一页
    private boolean hasNextPage;

    //分页后的数据
    private T data;



    public boolean getIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }


    public boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

}