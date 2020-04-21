package com.kstarrain.framework.api.dto.request;


import lombok.Data;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
public class BasePageRequest {

    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;

    public BasePageRequest toPageInfo() {
        BasePageRequest pageInfo = new BasePageRequest();
        pageInfo.setPageNum(pageNum == null ? 1 : pageNum);
        pageInfo.setPageSize(pageSize == null ? 10 : pageSize);
        pageInfo.setOrderBy(orderBy);
        return pageInfo;
    }

}
