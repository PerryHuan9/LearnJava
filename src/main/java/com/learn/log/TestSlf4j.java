package com.learn.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4j + Logback
 */
public class TestSlf4j {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static void learn() {
        var ts = new TestSlf4j();
        var logger = ts.logger;
        logger.trace("level: trace");
        logger.debug("level: debug");
        logger.info("level: info");
        logger.warn("level: warn");
        logger.error("level: error");
    }
}
