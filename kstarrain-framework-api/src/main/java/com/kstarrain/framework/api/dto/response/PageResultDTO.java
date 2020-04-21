package com.kstarrain.framework.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
@ApiModel("Restful 分页模型")
public class PageResultDTO<T> extends ResultDTO<List<T>> {

    @ApiModelProperty(value = "当前页", position = 3)
    private Integer pageNum;

    @ApiModelProperty(value = "每页展示条数", position = 4)
    private Integer pageSize;

    @ApiModelProperty(value = "总页数", position = 5)
    private Integer pageCount;

    @ApiModelProperty(value = "数据总数", position = 6)
    private Long total;

    public PageResultDTO() {
    	super();
    }
}
