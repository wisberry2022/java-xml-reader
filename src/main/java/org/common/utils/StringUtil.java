package org.common.utils;

public class StringUtil {

    public static int countContainedString(String str, String target) {
        return str.split(target, -1).length - 1;
    }

}
