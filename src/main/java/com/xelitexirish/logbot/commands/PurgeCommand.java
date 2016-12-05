package com.xelitexirish.logbot.commands;

import java.awt.Color;
import java.io.File;

import com.xelitexirish.logbot.handlers.FileHandler;
import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class PurgeCommand implements ICommand {

	FileHandler fileHandler = new FileHandler();
	
	private final String HELP_MSG = "Deletes the specified channel logs. Usage: 'purge channel <mentioned channels>' or 'purge temp'"; 
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {	
		if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
            if (args[0].equalsIgnoreCase("channel")) {
                for (TextChannel channels : event.getMessage().getMentionedChannels()) {
                	if (!event.getAuthor().hasPrivateChannel()) {
                		event.getAuthor().openPrivateChannel().queue(channel -> {
                			EmbedBuilder eb = new EmbedBuilder();
                			eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                			eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                			eb.setColor(Color.green);
                			eb.setTitle("Deleting the channel file(s)!");
                			eb.setDescription("The following channels will be deleted: " + channels);
                			MessageEmbed embed = eb.build();
                			channel.sendMessage(embed).queue();
                		});
                	} else {
                        event.getAuthor().getPrivateChannel().sendMessage("Deleting the channel file for the following channel: " + channels).queue();
                	}
                    FileHandler.getLogFile(event.getGuild(), channels).delete();
                }
            } else if (args[0].equalsIgnoreCase("temp")) {
                if (PermissionHandler.isUserMaintainer(event.getAuthor())) {
                	if (!event.getAuthor().hasPrivateChannel()) {
                		event.getAuthor().openPrivateChannel().queue(channel -> {
                			channel.sendMessage("Deleting the temp folder for LogBot").queue();
                		});
                	} else {
                		event.getAuthor().getPrivateChannel().sendMessage("Deleting the temp folder for LogBot").queue();
                	}
                    fileHandler.delete(new File(FileHandler.getBaseFileDir() + "temp"));
                }
            }
        } else {
        	if (!event.getAuthor().hasPrivateChannel()) {
        		event.getAuthor().openPrivateChannel().queue(channel -> {
        			channel.sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION)).queue();
        		});
        	} else {
                event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION)).queue();
        	}
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
		return "purge";
	}

}
