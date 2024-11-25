package com.gl.tiny.turms.im.util;

public class ThrowableUtil {

    private ThrowableUtil() {
    }

    public static int countCauses(Throwable t) {
        int i = 0;
        while ((t = t.getCause()) != null) {
            i++;
        }
        return i;
    }
}
