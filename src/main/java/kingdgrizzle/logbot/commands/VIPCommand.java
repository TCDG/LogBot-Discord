package kingdgrizzle.logbot.commands;

import kingdgrizzle.logbot.handlers.PermissionHandler;
import kingdgrizzle.logbot.handlers.VIPHandler;
import kingdgrizzle.logbot.utils.BotLogger;
import kingdgrizzle.logbot.utils.Constants;
import kingdgrizzle.logbot.utils.MessageUtils;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class VIPCommand implements ICommand {
	
	private final String HELP_MSG = "Use 'add' or 'remove' followed by a user mention to add them to vip logging. Otherwise use their user id.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		event.getAuthor().openPrivateChannel();
		if (args.length >= 1) {
            if (PermissionHandler.isUserAdmin(event.getGuild(), event.getAuthor())) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (event.getMessage().getMentionedUsers().size() > 0) {
                        for (User user : event.getMessage().getMentionedUsers()) {
                            VIPHandler.addUserToVip(event.getGuild(), event.getAuthor(), user);
                        }
                    } else {
                        User vipUser = event.getGuild().getMemberById(args[1]).getUser();
                        if (vipUser != null) {
                            VIPHandler.addUserToVip(event.getGuild(), event.getAuthor(), vipUser);
                        } else {
                            event.getAuthor().getPrivateChannel().sendMessage("No user found with id: " + args[1] + " on server: " + event.getGuild().getName()).queue();
                        }
                    }
                    BotLogger.info("Adding user to VIP list for server: " + event.getGuild().getName());

                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (event.getMessage().getMentionedUsers().size() > 0) {
                        for (User user : event.getMessage().getMentionedUsers()) {
                            VIPHandler.removeUserFromVip(event.getGuild(), event.getAuthor(), user);
                        }
                    } else {
                        User vipUser = event.getGuild().getMemberById(args[1]).getUser();
                        if (vipUser != null) {
                            VIPHandler.removeUserFromVip(event.getGuild(), event.getAuthor(), vipUser);
                        } else {
                            event.getAuthor().getPrivateChannel().sendMessage("No user found with id: " + args[1] + " on server: " + event.getGuild().getName()).queue();
                        }
                    }

                    BotLogger.info("Removing user from VIP list for server: " + event.getGuild().getName());
                }
            }else {
                event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION)).queue();
            }
        } else {
            MessageUtils.usefulError(event.getAuthor(), "`" + Constants.COMMAND_PREFIX + "vip add <mentioned user>`\n" + Constants.COMMAND_PREFIX + "vip add <user id>`\nor\n`" + Constants.COMMAND_PREFIX + "vip remove <mentioned user>`\n`" + Constants.COMMAND_PREFIX + "vip remove <user id>");
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
		return "vip";
	}

}
