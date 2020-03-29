package com.learn.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Commons Logging + Log4j
 *
 */
public class TestCommonsLogging {
    // 使用getClass()对于其继承的类也可以获取对应类的class
    public final  Log log= LogFactory.getLog(getClass());

    /**
     * Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），
     * 如果没有找到Log4j，再使用JDK Logging。
     */
    public static void learn() {
        TestCommonsLogging tsl = new TestCommonsLogging();
        Log log = tsl.log;
        log.trace("level: trace");
        log.debug("level: debug");
        log.info("level: info");
        log.warn("level: warn");
        log.error("level: error");
        log.fatal("level: fatal");
    }
}
