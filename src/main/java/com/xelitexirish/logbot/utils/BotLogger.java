package com.xelitexirish.logbot.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by XeliteXirish on 13/10/2016. www.xelitexirish.com
 */
public class BotLogger {

    static Logger logger;

    public static void init() {
        logger = Logger.getLogger("/logs/log.txt");

        try {
            FileHandler fileHandler = new FileHandler("/logs/log.log", true);
            logger.addHandler(fileHandler);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String log) {
        logger.info("[Log Bot] " + log);
    }

    public static void error(String log) {
        logger.info("[Log Bot: ERROR] " + log);
    }

    public static void debug(String log) {
        logger.info("[Log Bot: DEBUG] " + log);
    }

    public static void debug(String log, Exception exception){
        debug(log);
        exception.printStackTrace();
    }
}
