package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DiscordLogHandler {

    public static void onMessageRecieved(MessageReceivedEvent event) {

        //FileHandler.createNewChannel(event.getGuild(), event.getTextChannel());
        if (!event.getMessage().isPrivate()) {
            logMessage(event);
        }
    }

    private static void logMessage(MessageReceivedEvent event) {

        String logMessage = "{" + event.getMessage().getTime().toLocalDateTime() + "} " + "[" + event.getAuthor().getUsername() + "] " + event.getMessage().getContent();

        if(VipHandler.isUserVip(event.getGuild(), event.getAuthor())){
            logMessage = "{VIP}" + logMessage;
        }

        File logFile = FileHandler.getLogFile(event.getGuild(), event.getTextChannel());
        assert logFile != null;

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
            printWriter.println(logMessage);
            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
