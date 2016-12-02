package com.xelitexirish.logbot.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import com.xelitexirish.logbot.commands.GetCommand;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.GeneralUtils;
import com.xelitexirish.logbot.handlers.VIPHandler;

public class DiscordLogHandler {

	
	public static void onMessageReceived(MessageReceivedEvent event) {
		
		if (!event.getMessage().getChannelType().equals(ChannelType.PRIVATE)) {
            logMessage(event);
        }
	}
	
	private static void logMessage(MessageReceivedEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "{" + event.getGuild().getName() + "-" + event.getTextChannel() + "] " + "[" + event.getAuthor().getName() + "] " + event.getMessage().getContent();

        if (VIPHandler.isUserVip(event.getGuild(), event.getAuthor())) {
            logMessage = "{VIP} " + logMessage;
        }

        File logFile = FileHandler.getLogFile(event.getGuild(), event.getTextChannel());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the chat log file for the server: " + event.getGuild().getName());
    }

    public static void onMemberJoin(GuildMemberJoinEvent event) {
        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "User: " + event.getMember().getUser().getName() + " has joined the server: " + event.getGuild().getName();

        File logFile = FileHandler.getServerEventLogFile(event.getGuild());

        writeStringToFile(logFile, logMessage, "Wasn't able to write to the event log file for server: " + event.getGuild().getName());
    }

    public static void onMemberBan(GuildBanEvent event) {

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "User: " + event.getUser().getName() + " has been banned on the server: " + event.getGuild().getName();

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

        String logMessage = "{" + GeneralUtils.getCurrentTime() + "} " + "The user: " + event.getMember().getUser() + " changed their username to: " + event.getNewNick() + " on the server: " + event.getGuild().getName();

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

    public static void writePlayerDataLog(MessageReceivedEvent event, File logFile, User user, int searchLength) {

        if (logFile != null) {
            for (File file : FileHandler.getAllLogFiles()) {

                try {
                    @SuppressWarnings("resource")
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    int messageLength = 0;

                    while ((line = bufferedReader.readLine()) != null) {
                        messageLength++;
                        if (line.contains(user.getName())) {
                            writeStringToFile(logFile, line, "Wasn't able to write to the user log for the user: " + user.getName());
                        }

                        if (messageLength >= searchLength) break;
                        if (!PermissionHandler.isUserMaintainer(event.getAuthor())) {
                            if (messageLength >= GetCommand.MAX_LENGTH) break;
                        }
                    }
                } catch (IOException e) {
                    BotLogger.debug("Something broke writing to player log file!", e);
                }
            }
        }
    }
}
