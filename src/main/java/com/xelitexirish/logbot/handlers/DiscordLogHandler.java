package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.GeneralUtils;
import net.dv8tion.jda.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberBanEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.events.message.guild.GuildMessageDeleteEvent;

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

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "[" + event.getAuthor().getUsername() + "] " + event.getMessage().getContent();

        if (VipHandler.isUserVip(event.getGuild(), event.getAuthor())) {
            logMessage = "{VIP}" + logMessage;
        }

        File logFile = FileHandler.getLogFile(event.getGuild(), event.getTextChannel());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the chat log file for the server: " + event.getGuild().getName());
    }

    public static void onMemberJoin(GuildMemberJoinEvent event) {
        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "User: " + event.getUser().getUsername() + " has joined the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for server: " + event.getGuild().getName());
    }

    public static void onMemberBan(GuildMemberBanEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "User: " + event.getUser().getUsername() + " has been banned on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for server: " + event.getGuild().getName());
    }

    public static void onMessageDelete(GuildMessageDeleteEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following message has been deleted: " + event.getMessage().getContent() + " has deleted a message on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onTextChannelDelete(TextChannelDeleteEvent event) {
        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following text channel was deleted: " + event.getChannel().getName() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onTextChannelCreated(TextChannelCreateEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following text channel was created: " + event.getChannel().getName() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following voice channel was deleted: " + event.getChannel().getName() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onVoiceChannelCreate(VoiceChannelCreateEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following voice channel was created: " + event.getChannel().getName() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onUsernameUpdate(GuildMemberNickChangeEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The user: " + event.getUser().getUsername() + " changed their username to: " + event.getNewNick() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }


    /**
     * Helper Methods
     */
    private static void writeStringToFile(File logFile, String message, String errorMessage) {
        if (logFile != null && message != null) {
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
                printWriter.println(message);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                BotLogger.debug(errorMessage, e);
            }
        } else {
            BotLogger.error("LogFile or the message was null!");
        }
    }
}
