package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.GeneralUtils;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class StatusCommand implements ICommand {

	private final String HELP_MSG = "Displays the basic status information for the bot.  Use 'online' to set the bot's status to Online, 'offline' to set the bot's status to Invisible" ;
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		event.getAuthor().openPrivateChannel();
		if (args.length > 0 && args[0].equalsIgnoreCase("online")) {
            if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
                LogBot.setOnlineStatus();
                event.getAuthor().getPrivateChannel().sendMessage("Bot status has been changed to: " + event.getJDA().getPresence().getStatus()).queue();
                BotLogger.info(event.getAuthor().getName() + " set the bot status to: " + event.getJDA().getPresence().getStatus());
            } else {
                MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION);
            }
        } else if (args.length > 0 && args[0].equalsIgnoreCase("offline")) {
        	LogBot.setOfflineStatus();
        	event.getAuthor().getPrivateChannel().sendMessage("Bot status has been changed to: " + event.getJDA().getPresence().getStatus()).queue();
        	BotLogger.info(event.getAuthor().getName() + " set the bot status to: " + event.getJDA().getPresence().getStatus());
        } else {
            event.getTextChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(getStatusText(event), "css")).queue();
        }
	}

	@Override
	public String help() {
		return HELP_MSG;
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
        stringBuilder.append("Total Users: " + event.getJDA().getUsers().size() + "\n");
        stringBuilder.append("Servers: " + LogBot.jda.getGuilds().size() + "\n");
        stringBuilder.append("Total Channels: " + LogBot.getTotalChannels() + "\n");
        stringBuilder.append("Current Time: [" + GeneralUtils.getCurrentTime() + "]\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Guild Owner: " + event.getGuild().getOwner().getUser().getName() + "\n");
        stringBuilder.append("Guild ID: " + event.getGuild().getId() + "\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Bot Version: [" + Constants.BOT_VERSION + "]\n");
        stringBuilder.append("Bot Instance Maintainer: [" + event.getGuild().getMemberById(LogBot.MAINTAINER_ID).getUser().getName() + "]\n");
        stringBuilder.append("===================\n");
        stringBuilder.append("Bot Developer: XeliteXirish\n");
        stringBuilder.append("Developer Website: [www.xelitexirish.com]\n");
        stringBuilder.append("Ported to JDA 3.0 by: KingDGrizzle");
        return stringBuilder.toString();
    }

}
