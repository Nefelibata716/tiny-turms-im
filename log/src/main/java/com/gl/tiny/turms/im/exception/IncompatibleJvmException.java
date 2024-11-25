package com.gl.tiny.turms.im.exception;


public class IncompatibleJvmException extends RuntimeException {

    public IncompatibleJvmException(String message) {
        super(message);
    }

    public IncompatibleJvmException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleJvmException(Throwable cause) {
        super(cause);
    }
}