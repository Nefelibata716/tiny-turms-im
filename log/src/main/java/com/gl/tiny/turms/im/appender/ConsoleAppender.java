package com.gl.tiny.turms.im.appender;

import java.io.FileDescriptor;
import java.io.FileOutputStream;

//用来把日志输出到控制台的对象
public class ConsoleAppender extends Appender {


    public ConsoleAppender() {
        //得到把日志信息输出到控制台的channel
        channel = new FileOutputStream(FileDescriptor.out).getChannel();
    }

}