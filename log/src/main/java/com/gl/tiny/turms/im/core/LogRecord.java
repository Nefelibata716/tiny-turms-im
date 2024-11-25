package com.gl.tiny.turms.im.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    private LogLevel level;

    private String message;

}
