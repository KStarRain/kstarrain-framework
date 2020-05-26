package com.kstarrain.framework.common;

import com.kstarrain.framework.common.utils.JacksonUtils;
import org.junit.Test;

/**
 * @author: DongYu
 * @create: 2020-05-25 14:43
 * @description:
 */
public class JacksonUtilsTest {

    @Test
    public void test01(){
        System.out.println(JacksonUtils.toJSONString(1));
        System.out.println(JacksonUtils.toJSONString("hahaha"));
        System.out.println(JacksonUtils.toJSONString(true));
        System.out.println(JacksonUtils.toJSONString(0.005));
    }
}
