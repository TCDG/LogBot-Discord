package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.handlers.FileHandler;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

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
            File logFile = FileHandler.getLogFile(event.getGuild(), event.getTextChannel());
            MessageBuilder messageBuilder = new MessageBuilder();
            messageBuilder.appendString("This is the log file for channel: " + event.getTextChannel().getName());

            event.getAuthor().getPrivateChannel().sendFile(logFile, messageBuilder.build());

        } else {
            MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION);
        }
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
