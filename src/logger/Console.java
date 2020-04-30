package logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Console {
    private static Logger logger = null;

    private static void initLoggerRequired() {
        if (logger == null) {
            BasicConfigurator.configure();
            logger = LogManager.getRootLogger();
            logger.setLevel(Level.TRACE);
        }
    }

    public static void trace(Object msg) {
        initLoggerRequired();
        logger.trace(msg);
    }

    public static void error(Object msg) {
        initLoggerRequired();
        logger.error(msg);
    }

    public static void warn(Object msg) {
        initLoggerRequired();
        logger.warn(msg);
    }

    public static void setAssert(boolean assertion, String msg) {
        initLoggerRequired();
        logger.assertLog(assertion, msg);
    }
}
