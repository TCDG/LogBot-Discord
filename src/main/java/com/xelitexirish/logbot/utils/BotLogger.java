package com.xelitexirish.logbot.utils;

/**
 * Created by XeliteXirish on 13/10/2016. www.xelitexirish.com
 */
public class BotLogger {

    public static void info(String log) {
        System.out.println("[Log Bot] " + log);
    }

    public static void error(String log) {
        System.out.println("[Log Bot: ERROR] " + log);
    }

    public static void debug(String log) {
        System.out.println("[Log Bot: DEBUG] " + log);
    }
}
