package com.xelitexirish.logbot.commands;

import java.awt.Color;
import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements ICommand {

	private final String HELP_MSG = "Use '" + Constants.COMMAND_PREFIX + "help <command name>' to view more information about that command!";
	
	GetCommand getCmd = new GetCommand();
	PurgeCommand purgeCmd = new PurgeCommand();
	VIPCommand vipCmd = new VIPCommand();
	StatusCommand statusCmd = new StatusCommand();
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if (args.length == 0) {
			 EmbedBuilder embedBuilder = new EmbedBuilder();
			 embedBuilder.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
			 embedBuilder.setTitle("Description");
			 embedBuilder.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
			 embedBuilder.setDescription("Hey I'm LogBot, my author is XeliteXirish! Check his website out by clicking on the XeliteXirish text!");
			 embedBuilder.setColor(Color.GREEN);
			 String commands = "";
			 commands += "All commands must start with " + Constants.COMMAND_PREFIX + " !\n";
			 commands += "========================================================\n";
			 commands += "	" + getTag() + ": " + help() + "\n";
			 commands += "	" + getCmd.getTag() + ": " + getCmd.help() + "\n";
			 commands += "	" + purgeCmd.getTag() + ": " + purgeCmd.help() + "\n";
			 commands += "	" + vipCmd.getTag() + ": " + vipCmd.help() + "\n";
			 commands += "	" + statusCmd.getTag() + ": " + statusCmd.help();
			 embedBuilder.addField("Commands", commands, true);
			 embedBuilder.addBlankField(false);
			 MessageEmbed embed = embedBuilder.build();
             event.getTextChannel().sendMessage(embed).queue();
        } else {
            String helpCommand = args[0];
            ICommand command = getCommandFromString(helpCommand);
            sendHelpMessage(event, command);
        }
	}

	@Override
	public String help() {
		return HELP_MSG;
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {}

	@Override
	public String getTag() {
		return "help";
	}

	private ICommand getCommandFromString(String commandName) {
	    if (LogBot.commands.containsKey(commandName)){
	        return LogBot.commands.get(commandName);
	    } else {
		    return null;
	    }
	}
	
	private static void sendHelpMessage(MessageReceivedEvent event, ICommand command) {
	    if (command != null) {
	        if (command.help() != null) {
	        	EmbedBuilder eb = new EmbedBuilder();
	        	eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
	        	eb.setTitle("Here is the help message for the command: " + command.getTag());
	        	eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
	        	eb.setDescription("=========================================================\n" + command.help());
				eb.setColor(Color.green);
	        	MessageEmbed embed = eb.build();
	            event.getTextChannel().sendMessage(embed).queue();
	        } else {
	        	EmbedBuilder eb = new EmbedBuilder();
	        	eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
	        	eb.setTitle("Error in getting the help message for the command " + command.getTag() + "!");
	        	eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
	        	eb.setDescription("=========================================================\nThere is no information for that command!\n Please contact the bot maintainer for more information!");
	        	eb.setColor(Color.red);
	        	MessageEmbed embed = eb.build();
	        	event.getTextChannel().sendMessage(embed).queue();
	        }
	    } else {
	    	EmbedBuilder eb = new EmbedBuilder();
	    	eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
	    	eb.setTitle("Error in finding the command you have requested!!");
	    	eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
	    	eb.setDescription("=========================================================\nThe command you have requested does not exist!\nPlease run " + Constants.COMMAND_PREFIX + "help to see a list of available commands!");
	    	eb.setColor(Color.red);
	    	MessageEmbed embed = eb.build();
	    	event.getTextChannel().sendMessage(embed).queue();	    	
	    }
	}
}
