package com.gl.tiny.turms.im.appender;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;


/**
 * @date:2024/11/13
 * @方法描述：该类的对象会把日志写入到本地对应的文件中
 */
public class RollingFileAppender extends Appender {

    //StandardOpenOption.CREATE表示文件不存在则创建，已存在也不会抛出异常
    private static final Set<StandardOpenOption> APPEND_OPTIONS = Set.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

    //构造方法，file表示存储日志的文件的路径
    public RollingFileAppender(String file) throws IOException {

        //得到Path对象
        Path filePath = Paths.get(file).toAbsolutePath();

        //得到可以向文件中写入日志信息的channel
        //channel是父类的成员变量
        channel = FileChannel.open(filePath, APPEND_OPTIONS);
    }
}