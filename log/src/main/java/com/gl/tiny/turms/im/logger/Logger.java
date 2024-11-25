package com.gl.tiny.turms.im.logger;

import com.gl.tiny.turms.im.appender.Appender;
import com.gl.tiny.turms.im.util.ReferenceCountUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.List;

//注意，这个是我们自己实现的Logger类，并不是现成日志框架的类
public class Logger {

    //存放日志记录器要用到的Appender对象的集合
    private final List<Appender> appenders;

    //构造方法
    public Logger(List<Appender> appenders){
        this.appenders = appenders;
    }

    //记录日志的方法
    public void info(String var1){
        //创建一个LogRecord对象来封装要记录的日志信息,这里的这样代码其实是伪代码
        //大家先理解意思即可，后面重构代码的时候会详细展开
        ByteBuf byteBuf = Unpooled.wrappedBuffer(var1.getBytes());
        LogRecord logRecord = new LogRecord(System.currentTimeMillis(),byteBuf );
        for (Appender appender : appenders) {
            try {//遍历所有的Appender对象，把日志对象交给每一个Appender去处理
                //具体的处理流程就是把日志写入到对应的文件，或者是输出到控制台
                appender.append(logRecord);
            } catch (Exception e) {
                //InternalLogger.printException(e);
            }
        }//在这里，使用完毕netty的ByteBuf了，要记得释放资源
        ReferenceCountUtil.safeEnsureReleased(logRecord.getData());
    }
}