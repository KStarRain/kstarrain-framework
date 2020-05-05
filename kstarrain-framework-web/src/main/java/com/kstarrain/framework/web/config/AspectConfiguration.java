package com.kstarrain.framework.web.config;

import com.kstarrain.framework.web.aspect.AspectLog;
import com.kstarrain.framework.web.aspect.annotation.ExcludeAspectLog;
import com.kstarrain.framework.web.aspect.annotation.PrintAspectLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author: DongYu
 * @create: 2020-05-04 14:19
 * @description:
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@Import({AspectLog.class})
public class AspectConfiguration {

    public AspectConfiguration() {
        log.info("AspectLog {@Pointcut=@within({}) && !@annotation({}) init success", PrintAspectLog.class.getName(), ExcludeAspectLog.class.getName());
    }
}
