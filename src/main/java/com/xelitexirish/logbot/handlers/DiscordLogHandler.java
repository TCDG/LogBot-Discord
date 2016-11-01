package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.commands.GetCommand;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.GeneralUtils;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.events.guild.GuildJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberBanEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.events.message.guild.GuildMessageDeleteEvent;

import java.io.*;
import java.util.ArrayList;

public class DiscordLogHandler {

    public static void onMessageRecieved(MessageReceivedEvent event) {

        //FileHandler.createNewChannel(event.getGuild(), event.getTextChannel());
        if (!event.getMessage().isPrivate()) {
            logMessage(event);
        }
    }

    public static void onGuildJoin(GuildJoinEvent event) {
        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "{" + "I have joined the server: " + event.getGuild().getName() + "}";

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for server: " + event.getGuild().getName());
    }

    private static void logMessage(MessageReceivedEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "{" + event.getGuild().getName() + "-" + event.getTextChannel() + "] " + "[" + event.getAuthor().getUsername() + "] " + event.getMessage().getContent();

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

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "A message was deleted in the channel: " + event.getChannel() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onTextChannelDelete(TextChannelDeleteEvent event) {
        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "A text channel was deleted on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onTextChannelCreated(TextChannelCreateEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The following text channel was created: " + event.getChannel().getName() + " on the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for the server: " + event.getGuild().getName());
    }

    public static void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "A voice channel was deleted on the server: " + event.getGuild().getName();

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

    public static void writePlayerDataLog(MessageReceivedEvent event, File logFile, User user, int searchLength, boolean multiServer) {

        if (logFile != null) {
            for (String line : searchFoldersForText(event.getAuthor().getUsername(), event.getAuthor(), event.getGuild(), searchLength, multiServer)) {
                writeStringToFile(logFile, line, "Wasn't able to write to the user log for the user: " + user.getUsername());
            }
        }
    }

    public static ArrayList<String> searchFoldersForText(String searchText, User author, Guild guild, int searchLength, boolean multiServer) {
        ArrayList<String> arrayListLines = new ArrayList<>();

        if (multiServer) {
            for (File file : FileHandler.getAllLogFiles()) {

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    int messageLength = 0;

                    while ((line = bufferedReader.readLine()) != null) {
                        messageLength++;
                        if (line.contains(searchText)) {
                            arrayListLines.add(line);
                        }

                        if (messageLength >= searchLength && searchLength > 0) break;
                        if (!PermissionHandler.isUserMaintainer(author)) {
                            if (messageLength >= GetCommand.MAX_LENGTH) break;
                        }
                    }
                } catch (IOException e) {
                    BotLogger.debug("Something broke writing to player log file!", e);
                }
            }
        } else {
            for (File file : FileHandler.getAllServerLogFiles(guild)) {

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    int messageLength = 0;

                    while ((line = bufferedReader.readLine()) != null) {
                        messageLength++;
                        if (line.contains(searchText)) {
                            arrayListLines.add(line);
                        }
                        if (messageLength >= searchLength && searchLength > 0) break;
                        if (!PermissionHandler.isUserMaintainer(author)) {
                            if (messageLength >= GetCommand.MAX_LENGTH) break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayListLines;
    }
}
