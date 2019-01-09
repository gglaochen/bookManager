package com.chl.bookmanager.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
@Component
public class TestLogger {
    private Logger logger = LoggerFactory.getLogger(TestLogger.class);
    public void testLog(){
        logger.warn("警告日志");
    }
}
