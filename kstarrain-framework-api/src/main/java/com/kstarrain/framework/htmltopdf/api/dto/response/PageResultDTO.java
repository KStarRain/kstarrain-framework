package com.kstarrain.framework.htmltopdf.api.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
public class PageResultDTO<T> extends ResultDTO<List<T>> {

    private Integer pageNum;
    
    private Integer pageSize;
    
    private Integer pageCount;
    
    private Long total;

    public PageResultDTO() {
    	super();
    }
}
