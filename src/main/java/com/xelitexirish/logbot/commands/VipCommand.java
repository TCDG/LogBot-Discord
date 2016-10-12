package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.handlers.PermissionHandler;
import com.xelitexirish.logbot.handlers.VipHandler;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 12/10/2016. www.xelitexirish.com
 */
public class VipCommand implements ICommand {

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
                            VipHandler.addUserToVip(event.getGuild(), event.getAuthor(), user);
                        }
                    } else {
                        User vipUser = event.getGuild().getUserById(args[1]);
                        if (vipUser != null) {
                            VipHandler.addUserToVip(event.getGuild(), event.getAuthor(), vipUser);
                        } else {
                            event.getAuthor().getPrivateChannel().sendMessage("No user found with id: " + args[1] + " on server: " + event.getGuild().getName());
                        }
                    }
                    System.out.print("Adding user to VIP list for server: " + event.getGuild().getName());
                    
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (event.getMessage().getMentionedUsers().size() > 0) {
                        for (User user : event.getMessage().getMentionedUsers()) {
                            VipHandler.removeUserFromVip(event.getGuild(), event.getAuthor(), user);
                        }
                    } else {
                        User vipUser = event.getGuild().getUserById(args[1]);
                        if (vipUser != null) {
                            VipHandler.removeUserFromVip(event.getGuild(), event.getAuthor(), vipUser);
                        } else {
                            event.getAuthor().getPrivateChannel().sendMessage("No user found with id: " + args[1] + " on server: " + event.getGuild().getName());
                        }
                    }

                    System.out.print("Removing user from VIP list for server: " + event.getGuild().getName());
                }
            }else {
                event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.getNoPermissionMsg(PermissionHandler.ADMIN_PERMISSION));
            }
        } else {
            // Send info message
        }
    }

    @Override
    public String help() {
        return "Use 'add' or 'remove' followed by a user mention to add them to vip logging. Otherwise use their user id.";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "vip";
    }
}
