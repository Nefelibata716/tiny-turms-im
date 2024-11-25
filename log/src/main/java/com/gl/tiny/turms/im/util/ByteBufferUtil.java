package com.gl.tiny.turms.im.util;

import com.gl.tiny.turms.im.exception.IncompatibleJvmException;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public final class ByteBufferUtil {

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final MethodHandle INVOKE_CLEANER;

    static {
        Method method;
        try {
            method = UNSAFE.getClass()
                    .getDeclaredMethod("invokeCleaner", ByteBuffer.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleJvmException(
                    "Failed to find the method: sun.misc.Unsafe#invokeCleaner",
                    e);
        }
        INVOKE_CLEANER = ReflectionUtil.method2Handle(method);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1);
        freeDirectBuffer(buffer);
    }

    private ByteBufferUtil() {
    }

    public static ByteBuffer wrapAsDirect(byte[] bytes) {
        return ByteBuffer.allocateDirect(bytes.length)
                .put(bytes)
                .flip();
    }

    public static void freeDirectBuffer(ByteBuffer buffer) {
        try {
            INVOKE_CLEANER.invokeExact(UNSAFE, buffer);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Failed to free the direct buffer: "
                            + buffer,
                    e);
        }
    }

}