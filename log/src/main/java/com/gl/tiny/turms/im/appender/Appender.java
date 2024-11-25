package com.gl.tiny.turms.im.appender;

import com.gl.tiny.turms.im.logger.LogRecord;
import com.gl.tiny.turms.im.exception.InputOutputException;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @date:2024/11/13
 * @方法描述：这个类是ConsoleAppender和RollingFileAppender的父类，在该类中定义了处理日志的和新方法，那就是把日志写入到对应文件，或者输出到控制台的方法
 */
@Data
public abstract class Appender implements AutoCloseable {

    //把日志写入到对应文件，或者输出到控制台的channel
    //如果创建的是RollingFileAppender对象，那么这个channel就是用来把日志写入到对应本地文件的
    //如果创建的是ConsoleAppender，那么这个channel就是用来把日志输出到控制台的
    protected FileChannel channel;

     //关闭channel的方法
    @Override
    public void close() {
        try {
            channel.force(true);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Caught an error while forcing updates to the channel's file to be written",
                    e);
        }
        try {
            channel.close();
        } catch (IOException e) {
            throw new InputOutputException("Caught an error while closing the channel", e);
        }
    }


    /**
     * 方法描述：把日志写入到对应文件，或者输出到控制台的方法，如果创建的是ConsoleAppender对象，那就会直接调用父类的这个方法
     * 如果创建的是RollingFileAppender对象，那就会调用RollingFileAppender对象的append()方法，在该方法中，会调用父类的append()方法
     */
    public int append(LogRecord record) {
        //该方法的参数为LogRecord对象，这里我解释一下，一个LogRecord对象就封装着一条要记录的日志信息
        //日志信息被封装在LogRecord对象的ByteBuf成员变量中，现在大家还不知道LogRecord的具体内容
        //也不知道日志信息是怎么存放到ByteBuf对象中，这个ByteBuf可是netty内存池中的内存，使用完毕一定要记得释放，
        //但现在大家还不清楚这是怎么回事，所以大家可以先记着这一点，后面我会为大家具体讲解
        //这里就会得到日志内容
        ByteBuf buffer = record.getData();
        if (buffer.nioBufferCount() == 1) {
            try {//如果这个ByteBuf底层只有一个buffer，那就直接写入
                return channel.write(buffer.nioBuffer());
            } catch (IOException e) {
                throw new InputOutputException(
                        "Failed to write the buffer: "
                                + buffer,
                        e);
            }
        }
        int written = 0;
        //如果这个ByteBuf底层有多个buffer，那就循环写入
        for (ByteBuffer buf : buffer.nioBuffers()) {
            try {
                written += channel.write(buf);
            } catch (IOException e) {
                throw new InputOutputException(
                        "Failed to write the buffer: "
                                + buffer,
                        e);
            }
        }//返回写入到channel中的总字节数
        return written;
    }

}