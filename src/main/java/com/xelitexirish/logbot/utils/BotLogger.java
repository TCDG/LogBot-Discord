package com.xelitexirish.logbot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.xelitexirish.logbot.handlers.FileHandler;

public class BotLogger {
	
	private static File logFolder = new File("logs/");
	private static File logFile = new File(logFolder + "/logs.txt");
	
	private static void log(String LogMessage) {
		try {
			logFolder.mkdirs();
			if (!FileHandler.doesFileExist(logFile)) {
				logFile.createNewFile();
			}
			
			PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
			printWriter.println(LogMessage);
			printWriter.flush();
			printWriter.close();
			
			System.out.println(LogMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void info(String log) {
		log("[Log Bot] " + log);
	}
	
	public static void error(String log) {
        log("[Log Bot: ERROR] " + log);
    }

    public static void debug(String log) {
        log("[Log Bot: DEBUG] " + log);
    }

    public static void debug(String log, Exception exception) {
        debug(log);
        exception.printStackTrace();
    }
}
