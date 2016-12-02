package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements ICommand {

	private final String HELP_MSG = "Use '" + Constants.COMMAND_PREFIX + "help <command name>' to view more information about that command!";
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if (args.length == 0) {
            event.getTextChannel().sendMessage(getHelpMessage()).queue();
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
	public void executed(boolean success, MessageReceivedEvent event) {
	}

	@Override
	public String getTag() {
		return "help";
	}
	
    private Message getHelpMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hey I'm LogBot, my author is XeliteXirish! Check his website out (www.xelitexirish.com) \n");
        stringBuilder.append("To use a command, start it with: " + Constants.COMMAND_PREFIX + "\n\n");
        stringBuilder.append("The following commands can be used by the bot: \n");
        for (ICommand command : LogBot.commands.values()){
            stringBuilder.append("\t" + command.getTag() + ": " + command.help() + "\n");
        }

        return MessageUtils.wrapStringInCodeBlock(stringBuilder.toString(), "css");
    }

    private ICommand getCommandFromString(String commandName) {
        if (LogBot.commands.containsKey(commandName)){
            return LogBot.commands.get(commandName);
        }
        return null;
    }

    private static void sendHelpMessage(MessageReceivedEvent event, ICommand command) {
        if (command != null) {
            if (command.help() != null) {
                event.getTextChannel().sendMessage(command.help()).queue();;
            } else {
                event.getTextChannel().sendMessage("Sorry there is no info available for this command, please contact a bot administrator.").queue();;
            }
        } else {
            event.getTextChannel().sendMessage("Sorry but that is not a recognised command!").queue();;
        }
    }

}
