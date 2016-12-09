package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.handlers.VIPHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class VIPCommand implements ICommand {
	
	private final String HELP_MSG = "Use '/log vip add' or '/log vip remove' followed by a user mention to add them to VIP logging. Otherwise use their user id.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
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
                        	if (!event.getAuthor().hasPrivateChannel()) {
                        		event.getAuthor().openPrivateChannel().queue(channel -> {
									EmbedBuilder eb = new EmbedBuilder();
									eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
									eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
									eb.setColor(Color.red);
									eb.setTitle("Error while searching for that user ID!");
									eb.setDescription("No user found with ID: " + args[1] + " on server: " + event.getGuild().getName());
									MessageEmbed embed = eb.build();
                        			channel.sendMessage(embed).queue();
                        		});
                        	} else {
								EmbedBuilder eb = new EmbedBuilder();
								eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
								eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
								eb.setColor(Color.red);
								eb.setTitle("Error while searching for that user ID!");
								eb.setDescription("No user found with ID: " + args[1] + " on server: " + event.getGuild().getName());
								MessageEmbed embed = eb.build();
								event.getAuthor().getPrivateChannel().sendMessage(embed).queue();
                        	}
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
                        	if (!event.getAuthor().hasPrivateChannel()) {
                        		event.getAuthor().openPrivateChannel().queue(channel -> {
									EmbedBuilder eb = new EmbedBuilder();
									eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
									eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
									eb.setColor(Color.red);
									eb.setTitle("Error while searching for that user ID!");
									eb.setDescription("No user found with ID: " + args[1] + " on server: " + event.getGuild().getName());
									MessageEmbed embed = eb.build();
                        			channel.sendMessage(embed).queue();
                        		});
                        	} else {
								EmbedBuilder eb = new EmbedBuilder();
								eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
								eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
								eb.setColor(Color.red);
								eb.setTitle("Error while searching for that user ID!");
								eb.setDescription("No user found with ID: " + args[1] + " on server: " + event.getGuild().getName());
								MessageEmbed embed = eb.build();
								event.getAuthor().getPrivateChannel().sendMessage(embed).queue();
                        	}
                        }
                    }
                    BotLogger.info("Removing user from VIP list for server: " + event.getGuild().getName());
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
