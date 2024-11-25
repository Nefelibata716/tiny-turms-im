package com.gl.tiny.turms.im.logger;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    private LogLevel level;

    private long timestamp;

    private ByteBuf data;



    public LogRecord( long timestamp, ByteBuf data) {
        this.timestamp = timestamp;
        this.data = data;
    }



}
