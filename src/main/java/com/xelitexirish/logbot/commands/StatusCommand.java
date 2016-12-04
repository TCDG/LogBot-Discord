package com.xelitexirish.logbot.commands;

import java.awt.Color;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.GeneralUtils;
import com.xelitexirish.logbot.utils.MessageUtils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class StatusCommand implements ICommand {

	private final String HELP_MSG = "Displays the basic status information for the bot.\nUse 'online' to set the bot's status to Online, 'offline' to set the bot's status to Invisible";
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if (args.length > 0 && args[0].equalsIgnoreCase("online")) {
            if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
                LogBot.setOnlineStatus();
                if (!event.getAuthor().hasPrivateChannel()) {
                	event.getAuthor().openPrivateChannel().queue(channel -> {
                		EmbedBuilder eb = new EmbedBuilder();
                		eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                		eb.setColor(Color.green);
                		eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                		eb.setTitle("Bot status has been changed!");
                		eb.setDescription("Current status: " + event.getJDA().getPresence().getStatus());                		
                		MessageEmbed embed = eb.build();                		
                		channel.sendMessage(embed).queue();
                	});
            	} else {
            		EmbedBuilder eb = new EmbedBuilder();
            		eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
            		eb.setColor(Color.green);
            		eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
            		eb.setTitle("Bot status has been changed!");
            		eb.setDescription("Current status: " + event.getJDA().getPresence().getStatus());                		
            		MessageEmbed embed = eb.build();
            		event.getAuthor().getPrivateChannel().sendMessage(embed).queue();
            	}      
                BotLogger.info(event.getAuthor().getName() + " set the bot status to: " + event.getJDA().getPresence().getStatus());
            } else {
                MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION);
            }
        } else if (args.length > 0 && args[0].equalsIgnoreCase("offline")) {
        	if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
        		LogBot.setOfflineStatus();
        		if (!event.getAuthor().hasPrivateChannel()) {
        			event.getAuthor().openPrivateChannel().queue(channel -> {
        				EmbedBuilder eb = new EmbedBuilder();
                		eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                		eb.setColor(Color.gray);
                		eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                		eb.setTitle("Bot status has been changed!");
                		eb.setDescription("Current status: " + event.getJDA().getPresence().getStatus());                		
                		MessageEmbed embed = eb.build();
        				channel.sendMessage(embed).queue();
        			});
            	} else {
    				EmbedBuilder eb = new EmbedBuilder();
            		eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
            		eb.setColor(Color.gray);
            		eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
            		eb.setTitle("Bot status has been changed!");
            		eb.setDescription("Current status: " + event.getJDA().getPresence().getStatus());                		
            		MessageEmbed embed = eb.build();
            		event.getAuthor().getPrivateChannel().sendMessage(embed).queue();
            	}
        		BotLogger.info(event.getAuthor().getName() + " set the bot status to: " + event.getJDA().getPresence().getStatus());
        	} else {
        		MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION);
        	}
        } else {
        	EmbedBuilder eb = new EmbedBuilder();
        	eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
        	eb.setColor(Color.cyan);
        	eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
        	eb.setTitle("[-!.LotBot Status.!-]");
        	String description = "";
        	description += "Total Users: " + event.getJDA().getUsers().size() + "\n";
        	description += "Servers: " + LogBot.jda.getGuilds().size() + "\n";
        	description += "Total Channels: " + LogBot.getTotalChannels() + "\n";
        	description += "Current Time: [" + GeneralUtils.getCurrentTime() + "]";
        	eb.setDescription(description);
        	String guildInfo = "";
        	guildInfo += "Guild Owner: " + event.getGuild().getOwner().getUser().getName() + "\n";
        	guildInfo += "Guild ID: " + event.getGuild().getId();
        	eb.addField("===================", guildInfo, false);
        	String botInfo = "";
        	botInfo += "Bot Version: [" + Constants.BOT_VERSION + "]\n";
        	botInfo += "Bot Instance Maintainer: [" + event.getGuild().getMemberById(LogBot.MAINTAINER_ID).getUser().getName() + "]";
        	eb.addField("===================", botInfo, false);
        	String general = "";
        	general += "Bot Developer: XeliteXirish\n";
        	general += "Developer Website: [http://www.xelitexirish.com]\n";
        	general += "Ported to JDA 3.0 by: KingDGrizzle";
        	eb.addField("===================", general, false);
        	MessageEmbed embed = eb.build();
        	event.getTextChannel().sendMessage(embed).queue();
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
}
