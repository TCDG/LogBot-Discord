package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.GeneralUtils;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 15/10/2016. www.xelitexirish.com
 */
public class StatusCommand implements ICommand {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length > 0 && args[0].equalsIgnoreCase("online")) {
            if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
                LogBot.toggleOnlineStatus();
                event.getAuthor().getPrivateChannel().sendMessage("Bot status has been changed to: " + event.getJDA().getSelfInfo().getOnlineStatus());
                BotLogger.info(event.getAuthorName() + " set the bot status to: " + event.getJDA().getSelfInfo().getOnlineStatus());
            }else {
                MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION);
            }
        } else {
            event.getTextChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(getStatusText(event), "css"));
        }
    }

    @Override
    public String help() {
        return "Displays the basic status information for the bot.  Use 'online' as an extra argument to toggle the bots online status";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "status";
    }

    private String getStatusText(MessageReceivedEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[-!.LotBot Status.!-]\n\n");
        stringBuilder.append("Total Users: " + LogBot.getTotalMembers() + "\n");
        stringBuilder.append("Servers: " + LogBot.jda.getGuilds().size() + "\n");
        stringBuilder.append("Total Channels: " + LogBot.getTotalChannels() + "\n");
        stringBuilder.append("Current Time: [" + GeneralUtils.getCurrentTime() + "]\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Guild Owner: " + event.getGuild().getOwner().getUsername() + "\n");
        stringBuilder.append("Guild ID: " + event.getGuild().getId() + "\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Bot Version: [" + Constants.BOT_VERSION + "]\n");
        stringBuilder.append("Bot Instance Maintainer: [" + event.getGuild().getUserById(LogBot.MAINTAINER_ID).getUsername() + "]\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Bot Developer: XeliteXirish\n");
        stringBuilder.append("Developer Website: [www.xelitexirish.com]");
        return stringBuilder.toString();
    }
}
