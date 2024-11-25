package com.gl.tiny.turms.im.util;

import java.lang.reflect.Array;

/**
 * @author James Chen
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static Object[] getArray(Object value) {
        if (value instanceof Object[] values) {
            return values;
        }
        int length = Array.getLength(value);
        Object[] outputArray = new Object[length];
        for (int i = 0; i < length; ++i) {
            outputArray[i] = Array.get(value, i);
        }
        return outputArray;
    }

}