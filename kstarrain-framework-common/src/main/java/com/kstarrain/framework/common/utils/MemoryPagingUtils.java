package com.kstarrain.framework.common.utils;

import com.kstarrain.framework.common.utils.extra.MemoryPageInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: DongYu
 * @create: 2019-07-20 15:49
 * @description: 内存分页工具类
 */
public class MemoryPagingUtils {

    /**
     * 内存分页
     *
     * @param pageNum
     * @param pageSize
     * @param resList
     * @param <T>
     * @return
     */
    //对结果集进行内存分页
    public static <T> MemoryPageInfo<List<T>> pagingList(int pageNum, int pageSize, List<T> resList) {

        // 总条数
        int total = 0;
        if (CollectionUtils.isNotEmpty(resList)) {
            total = resList.size();
        }

        // 总页数
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

        // 上一页
        int prePage = 1;
        if (pageNum > 1) {
            prePage = pageNum - 1;
        }

        // 下一页
        int nextPage = pageNum;
        if (pageNum < pages) {
            nextPage = pageNum + 1;
        }

        MemoryPageInfo<List<T>> pageInfo = new MemoryPageInfo<>();
        pageInfo.setData(new ArrayList<>());
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages(pages);
        pageInfo.setPrePage(prePage);
        pageInfo.setNextPage(nextPage);
        pageInfo.setTotal(total);
        pageInfo.setIsFirstPage(pageNum == 1);
        pageInfo.setIsLastPage(pageNum == pages);
        pageInfo.setHasPreviousPage(pageNum > 1);
        pageInfo.setHasNextPage(pageNum < pages);

        if (CollectionUtils.isEmpty(resList)) {
            return pageInfo;
        }

        //起始索引
        int startIndex = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        //结束索引
        int endIndex = Math.min(startIndex + pageSize, total);

        if (startIndex < endIndex) {
            pageInfo.setData(resList.subList(startIndex, endIndex));
        }
        return pageInfo;
    }

}