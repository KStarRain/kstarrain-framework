package com.kstarrain.framework.htmltopdf.api.dto.response;

import lombok.Data;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
public class ResultDTO<T> {

    private Boolean success;
    
    private String code;
    
    private String message;
    
    private T data;

    public ResultDTO() {
        this.success = true;
    }

    public ResultDTO(T data) {
        this.success = true;
        this.data = data;
    }
}
