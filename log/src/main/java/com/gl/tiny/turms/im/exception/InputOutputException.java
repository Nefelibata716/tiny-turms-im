package com.gl.tiny.turms.im.exception;


public class InputOutputException extends RuntimeException {

    public InputOutputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputOutputException(String message) {
        super(message);
    }

    public InputOutputException(Exception cause) {
        super(cause);
    }

}