package com.kstarrain.framework.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: DongYu
 * @create: 2020-04-02
 * @description:
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("Restful 基础模型")
public class ResultDTO<T> {

    @ApiModelProperty(value = "业务标识（true：成功; false：失败）", position = 1, required = true, example = "true")
    private Boolean success;

    @ApiModelProperty(value = "数据", position = 2)
    private T data;

    @ApiModelProperty(value = "业务编码", position = 7)
    private String code;

    @ApiModelProperty(value = "业务信息", position = 8)
    private String message;



    public ResultDTO() {
        this.success = true;
    }

    public ResultDTO(T data) {
        this.success = true;
        this.data = data;
    }
}
