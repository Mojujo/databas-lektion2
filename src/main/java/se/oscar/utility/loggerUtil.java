package se.oscar.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class loggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(loggerUtil.class);

    private loggerUtil() {
        // Private constructor to prevent instantiation
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}