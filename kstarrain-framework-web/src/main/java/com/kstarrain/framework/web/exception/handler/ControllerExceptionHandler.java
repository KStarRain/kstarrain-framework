package com.kstarrain.framework.web.exception.handler;

import com.kstarrain.framework.api.dto.response.ResultDTO;
import com.kstarrain.framework.common.utils.JacksonUtils;
import com.kstarrain.framework.web.exception.BizException;
import com.kstarrain.framework.web.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Dong Yu
 * @create: 2019-07-26 10:57
 * @description: 全局处理Controller异常（但无法处理filter的异常）
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    public ControllerExceptionHandler() {
        log.info("@Import {className={}) load success", this.getClass().getName());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResultDTO> handleException(HttpServletRequest request, Exception e) {

        ResultDTO<Object> result = new ResultDTO<>();
        result.setSuccess(false);
        if (e instanceof BizException) {
            result.setCode(((BizException) e).getErrorCode());
            result.setMessage(((BizException) e).getErrorMessage());
            log.error("【业务异常】-- 异常信息：[{}] [{}]", ((BizException) e).getErrorCode(),((BizException) e).getErrorMessage() );
        } else {
            result.setCode("SYSTEM ERROR");
            result.setMessage("系统异常");
            log.error("【系统异常】-- 异常信息：{}", e.getMessage(), e);
        }

        log.error("Response Information : ↓ {}", LogUtils.packageRes(HttpStatus.OK.value(), JacksonUtils.toJSONString(result)));
        return ResponseEntity.ok(result);
    }




}
