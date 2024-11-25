package com.gl.tiny.turms.im.logger;

import com.gl.tiny.turms.im.appender.Appender;
import com.gl.tiny.turms.im.appender.ConsoleAppender;
import com.gl.tiny.turms.im.appender.RollingFileAppender;

import java.util.ArrayList;
import java.util.List;

/**
 * @date:2024/11/12
 * @方法描述：日志工厂对象，该对象就是用来获得具体的Logger对象的
 */
public class LoggerFactory {

    //存储默认Appender的集合，所谓默认使用的Appender，其实就是ConsoleAppender和RollingFileAppender这两个日志对象
    private static final List<Appender> DEFAULT_APPENDERS = new ArrayList<>(2);

    //日志组件是否初始化完毕的标志
    private static boolean initialized;
    
    //无参构造
    private LoggerFactory() {
    
    }


    /**
     * @方法描述：初始化日志系统的方法
     */
    public static synchronized void init(String file) {
        //判断日志系统是否已初始化完毕，如果初始化过了，直接退出当前方法
        if (initialized) {
            return;
        }
        //创建ConsoleAppender对象
        ConsoleAppender consoleAppender = new ConsoleAppender();
        //把刚创建好的ConsoleAppender对象添加到DEFAULT_APPENDERS集合中
        DEFAULT_APPENDERS.add(consoleAppender);
        //创建RollingFileAppender对象
        RollingFileAppender fileAppender = new RollingFileAppender(file);
        //把刚创建好的RollingFileAppender对象添加到DEFAULT_APPENDERS集合中
        DEFAULT_APPENDERS.add(fileAppender);
        initialized = true;
    }
    
    public static Logger getLogger(Class<?> clazz) {
        //首先判断日志系统是否已经初始化完毕，初始化完毕后才会进入正常的执行流程
        if (initialized) {
            return new Logger(DEFAULT_APPENDERS);
        }

        return null;
    }
    
}