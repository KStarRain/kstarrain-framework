package com.kstarrain.framework.api.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
@ApiModel("分页查询 基础条件")
public class BasePageRequest {

    @ApiModelProperty(value = "当前页", position = 1, example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页展示条数", position = 2, example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "排序字段", example = "id")
    private String orderBy;

    @ApiModelProperty(value = "排序规则（true：升序，false：降序）", allowableValues = "true, false", example = "false")
    private boolean asc;

    public BasePageRequest toPageInfo() {
        BasePageRequest pageInfo = new BasePageRequest();
        pageInfo.setPageNum(pageNum == null ? 1 : pageNum);
        pageInfo.setPageSize(pageSize == null ? 10 : pageSize);
        pageInfo.setOrderBy(orderBy);
        pageInfo.setAsc(asc);
        return pageInfo;
    }

}
