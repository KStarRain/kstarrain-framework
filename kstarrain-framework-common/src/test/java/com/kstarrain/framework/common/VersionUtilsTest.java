package com.kstarrain.framework.common;

import com.kstarrain.framework.common.utils.VersionUtils;
import org.junit.Test;

/**
 * @author: DongYu
 * @create: 2020-05-05 10:39
 * @description:
 */
public class VersionUtilsTest {

    @Test
    public void test01(){
        System.out.println(VersionUtils.lessEqual("wo", "3.1.0"));
    }
}
