package com.gl.tiny.turms.im.util;

import io.netty.util.ReferenceCounted;

/**
 * @author James Chen
 */
public final class ReferenceCountUtil {

    private ReferenceCountUtil() {
    }

    public static void ensureReleased(ReferenceCounted value) {
        int refCnt = value.refCnt();
        if (refCnt > 0) {
            value.release(refCnt);
        }
    }

    public static void ensureReleased(ReferenceCounted[] values) {
        for (ReferenceCounted value : values) {
            if (value != null) {
                ensureReleased(value);
            }
        }
    }

    public static void ensureReleased(ReferenceCounted[] values, int start, int end) {
        ReferenceCounted value;
        for (int i = start; i < end; i++) {
            value = values[i];
            if (value != null) {
                ensureReleased(value);
            }
        }
    }

    public static void safeEnsureReleased(ReferenceCounted value) {
        try {
            int refCnt = value.refCnt();
            if (refCnt > 0) {
                value.release(refCnt);
            }
        } catch (Exception ignored) {
        }
    }

}