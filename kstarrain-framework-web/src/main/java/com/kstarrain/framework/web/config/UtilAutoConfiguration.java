package com.kstarrain.framework.web.config;

import com.kstarrain.framework.web.aspect.AspectLog;
import com.kstarrain.framework.web.exception.handler.ControllerExceptionHandler;
import com.kstarrain.framework.web.filter.RequestWrapperFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: DongYu
 * @create: 2020-05-04 14:19
 * @description:
 */
@Slf4j
@Configuration
@Import({AspectLog.class, RequestWrapperFilter.class, ControllerExceptionHandler.class})
public class UtilAutoConfiguration {

    public UtilAutoConfiguration() {
        log.info("@Import {className={}) load success", AspectLog.class.getName());
        log.info("@Import {className={}) load success", RequestWrapperFilter.class.getName());
        log.info("@Import {className={}) load success", ControllerExceptionHandler.class.getName());
    }
}
