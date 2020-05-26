/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.kstarrain.framework.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

/**
 * @author: Dong Yu
 * @create: 2019-11-28 11:28
 * @description: Xml转换工具类 (需配合xStream相关注解)
 */
public final class XmlConvertUtils {


    private XmlConvertUtils() {}


    public static <T> String beanToXml(T bean) {
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        xStream.processAnnotations(bean.getClass());
        return xStream.toXML(bean);
    }



    public static <T> T xmlToBean(String src, Class<T> distClass) {
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        xStream.processAnnotations(distClass);
        return (T)xStream.fromXML(src);
    }

}
