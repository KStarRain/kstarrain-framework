package com.kstarrain.framework.common.utils;

import java.util.UUID;

public final class UUIDUtils {
    private UUIDUtils() {
    }

    public static String newUuid(String separator) {
        return UUID.randomUUID().toString().replace("-", separator);
    }

    public static String newUuid() {
        return newUuid("");
    }
}