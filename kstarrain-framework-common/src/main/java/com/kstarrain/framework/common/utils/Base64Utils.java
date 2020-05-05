package com.kstarrain.framework.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

    private Base64Utils() {
    }

    public static String decodeToString(String base64) {
        try {
            return new String(Base64.decodeBase64(base64),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8: " + e);
        }
    }

    public static byte[] decode(String base64) {
        return Base64.decodeBase64(base64);
    }

    public static String encodeToString(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static String encodeToString(String src) {
        try {
            return Base64.encodeBase64String(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8: " + e);
        }
    }
}