package com.kstarrain.framework.web.aspect;

import com.kstarrain.framework.api.dto.response.ResultDTO;
import com.kstarrain.framework.common.utils.JacksonUtils;
import com.kstarrain.framework.web.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: DongYu
 * @create: 2020-04-27 14:32
 * @description: aop打印request和response日志
 */
@Slf4j
@Aspect
public class AspectLog {

    public AspectLog() {
        log.info("@Import {className={}) load success",this.getClass().getName());
    }

    /**
     * 切点
     */
    @Pointcut("@annotation(com.kstarrain.framework.web.aspect.annotation.PrintAspectLog) " +
            "|| (@within(com.kstarrain.framework.web.aspect.annotation.PrintAspectLog) && !@annotation(com.kstarrain.framework.web.aspect.annotation.ExcludeAspectLog))")
    public void pointCut() {
    }

    /**
     * 在方法调用之前，打印入参
     */
    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        // 打印请求日志
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();
        List<MultipartFile> files = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof MultipartFile){
                files.add((MultipartFile) arg);
            }
        }
        log.info("【Request Information】 : ↓ {}", LogUtils.packageReq(request, files));
    }


    /**
     * 返回之后，打印出参
     */
    @AfterReturning(value = "pointCut()", returning = "returnObject")
    public void afterReturn(Object returnObject) {

        if (returnObject instanceof ResultDTO){
            if (((ResultDTO) returnObject).getSuccess()){
                log.info("【Response Information】 : ↓ {}", LogUtils.packageRes(HttpStatus.OK.value(), JacksonUtils.toJSONString(returnObject)));
            } else {
                log.error("【Response Information】 : ↓ {}", LogUtils.packageRes(HttpStatus.OK.value(), JacksonUtils.toJSONString(returnObject)));
            }

        } else if (returnObject instanceof ResponseEntity)  {
            if (((ResponseEntity) returnObject).getStatusCode() == HttpStatus.OK){
                log.info("【Response Information】 : ↓ {}", LogUtils.packageRes(((ResponseEntity) returnObject).getStatusCode().value(), JacksonUtils.toJSONString(((ResponseEntity) returnObject).getBody())));
            } else {
                log.error("【Response Information】 : ↓ {}", LogUtils.packageRes(((ResponseEntity) returnObject).getStatusCode().value(), JacksonUtils.toJSONString(((ResponseEntity) returnObject).getBody())));
            }
        } else if (returnObject != null){
            if (returnObject instanceof ModelAndView){
                log.info("【Response Information】 : ↓ {}", LogUtils.packageRes(returnObject.toString()));
            } else {
                log.info("【Response Information】 : ↓ {}", LogUtils.packageRes(JacksonUtils.toJSONString(returnObject)));
            }
        }
    }



//    /** 异常处理 */
//    @AfterThrowing(value = "pointCut()", throwing = "e")
//    public void afterThrowing(Throwable e) {
//        if (e instanceof BizException) {
//            log.error("【业务异常】-- 异常信息：[{}] [{}]", ((BizException) e).getErrorCode(),((BizException) e).getErrorMessage() );
//        } else {
//            log.error("【系统异常】-- 异常信息：{}", e.getMessage(), e);
//        }
//    }


}
