package com.gl.tiny.turms.im;

import com.gl.tiny.turms.im.logger.Logger;
import com.gl.tiny.turms.im.logger.LoggerFactory;

public class Test {

    public static void main(String[] args) {
    
        //下面是我提供的测试例子
        //初始化日志工厂
        LoggerFactory.init("/Users/guoliang/code/github/tiny-turms-im/data/log");
        //得到日志对象
        Logger logger = LoggerFactory.getLogger(Test.class);
        
        //记录日志，并且在控制台输出日志
        logger.info("啊啊啊啊啊啊啊啊啊，左转的红绿灯最难等了！！！！！！");
       
    }
}