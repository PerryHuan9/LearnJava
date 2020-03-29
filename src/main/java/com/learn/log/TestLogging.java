package com.learn.log;

import java.util.logging.Logger;

public class TestLogging {
    public static void learn() {
        Logger logger = Logger.getGlobal();
        // logger 默认只打印info及以上级别，logger的级别只能通过配置文件修改，一旦执行main方法则无法修改
        logger.finest("level: finest");
        logger.finer("level: finer");
        logger.fine("level: fine");
        logger.config("level: config");
        logger.info("level: info");
        logger.warning("level: warning");
        logger.severe("level: severe");

    }

}
