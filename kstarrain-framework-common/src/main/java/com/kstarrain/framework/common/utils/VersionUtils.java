package com.kstarrain.framework.common.utils;


/**
 * @author: Dong Yu
 * @create: 2019-06-12 18:27
 * @description:
 */
public class VersionUtils {

    /**
     * 判断version1是否小于version2
     *
     * @param version1
     * @param version2
     * @return version1小于version2返回true
     */
    public static boolean lessThan(String version1, String version2) {
        return compareVersion(version1, version2) < 0;
    }

    /**
     * 判断version1是否小于等于version2
     *
     * @param version1
     * @param version2
     * @return version1小于或者等于version2返回true
     */
    public static boolean lessEqual(String version1, String version2) {
        return (compareVersion(version1, version2) < 0) || (compareVersion(version1, version2) == 0);
    }

    /**
     * 判断version1是否大于version2
     *
     * @param version1
     * @param version2
     * @return version1大于version2返回true
     */
    public static boolean greaterThan(String version1, String version2) {
        return compareVersion(version1, version2) > 0;
    }

    /**
     * 判断version1是否大于等于version2
     *
     * @param version1
     * @param version2
     * @return version1大于或者等于version2返回true
     */
    public static boolean greaterEqual(String version1, String version2) {
        return (compareVersion(version1, version2) > 0) || (compareVersion(version1, version2) == 0);
    }

    /**
     * 判断version1是否等于version2
     *
     * @param version1
     * @param version2
     * @return version1等于version2返回true
     */
    public static boolean equals(String version1, String version2) {
        return compareVersion(version1, version2) == 0;
    }


    private static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\."); //通过\\将.进行转义
        String[] s2 = version2.split("\\.");
        int len1 = s1.length;
        int len2 = s2.length;
        int i, j;
        for (i = 0, j = 0; i < len1 && j < len2; i++, j++) {
            if (Integer.parseInt(s1[i]) > Integer.parseInt(s2[j])) {
                return 1;
            } else if (Integer.parseInt(s1[i]) < Integer.parseInt(s2[j])) {
                return -1;
            }
        }
        while (i < len1) {
            if (Integer.parseInt(s1[i]) != 0) {
                return 1;
            }
            i++;
        }
        while (j < len2) {
            if (Integer.parseInt(s2[j]) != 0) {
                return -1;
            }
            j++;
        }
        return 0;
    }
}
