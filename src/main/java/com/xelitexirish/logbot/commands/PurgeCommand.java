package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.handlers.FileHandler;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 24/10/2016. www.xelitexirish.com
 */
public class PurgeCommand implements ICommand {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
            if (args[0].equalsIgnoreCase("channel")) {
                for (TextChannel channel : event.getMessage().getMentionedChannels()) {
                    event.getAuthor().getPrivateChannel().sendMessage("Deleting the channel file for the following channel: " + channel);
                    FileHandler.getLogFile(event.getGuild(), channel).delete();
                }

            } else if (args[0].equalsIgnoreCase("temp")) {
                if (PermissionHandler.isUserMaintainer(event.getAuthor())) {
                    event.getAuthor().getPrivateChannel().sendMessage("Deleting the temp folder for LogBot");
                    FileHandler.getTempFolder().delete();
                } else {
                    event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION));
                }
            }
        } else {
            event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION));
        }
    }

    @Override
    public String help() {
        return "Deletes the specified channel. Usage: 'purge <mentioned channels>'";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "purge";
    }
}
