package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.handlers.FileHandler;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.exceptions.RateLimitedException;

import java.io.File;

/**
 * Created by XeliteXirish on 15/10/2016. www.xelitexirish.com
 */
public class GetCommand implements ICommand {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
            try {
                if (args.length > 0) {

                    if (args[0].equalsIgnoreCase("all")) {
                        File[] channelFiles = FileHandler.getAllServerFiles(event.getGuild());
                        if (channelFiles != null) {
                            event.getAuthor().getPrivateChannel().sendMessage("Here are the chats logs for the server: " + event.getGuild().getName());

                            for (File file : channelFiles) {
                                event.getAuthor().getPrivateChannel().sendFile(file, null);
                                BotLogger.info(event.getAuthorName() + " asked for file: " + file.getName() + " on server: " + event.getGuild().getName());
                            }
                        } else {
                            event.getAuthor().getPrivateChannel().sendMessage("Sorry but an error occurred or there was no chat logs found!");
                        }

                    } else if (event.getMessage().getMentionedChannels().size() > 0) {

                        event.getAuthor().getPrivateChannel().sendMessage("Here are the chat logs for the channels you asked for:");
                        for (TextChannel channel : event.getMessage().getMentionedChannels()) {
                            File channelFile = FileHandler.getLogFile(event.getGuild(), channel);
                            event.getAuthor().getPrivateChannel().sendFile(channelFile, null);


                            BotLogger.info(event.getAuthorName() + " asked for file: " + channelFile.getName() + " on server: " + event.getGuild().getName());
                        }

                    } else {
                        sendHelpMessage(event);
                    }
                } else {

                    File logFile = FileHandler.getLogFile(event.getGuild(), event.getTextChannel());
                    MessageBuilder messageBuilder = new MessageBuilder();
                    messageBuilder.appendString("This is the log file for channel: " + event.getTextChannel().getName());

                    event.getAuthor().getPrivateChannel().sendFile(logFile, messageBuilder.build());
                    BotLogger.info(event.getAuthorName() + " asked for file: " + logFile.getName() + " on server: " + event.getGuild().getName());
                }
            } catch (RateLimitedException e) {
                BotLogger.error("I got rate limited sending chat logs for the server: " + event.getGuild().getName());
            }
        } else {
            event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION));
        }
    }

    private void sendHelpMessage(MessageReceivedEvent event) {
    }

    @Override
    public String help() {
        return "Returns the log file for the specified channel";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "get";
    }
}
