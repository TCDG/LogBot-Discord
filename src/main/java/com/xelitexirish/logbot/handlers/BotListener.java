package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Commands
        if (event.getMessage().getContent().startsWith(Constants.COMMAND_PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
            LogBot.handleCommand(LogBot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
        }

        if (!event.getAuthor().isBot() && event.getMessage().isPrivate()){
            event.getAuthor().getPrivateChannel().sendMessage("Sorry but I can't recognise any command or input you make unless its made in a guild! I need to make sure you have permission!");
        }

        DiscordLogHandler.onMessageRecieved(event);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if (event.getUser().getId().equals(LogBot.jda.getSelfInfo().getId())){
            BotLogger.info("I joined the server: " + event.getGuild());
        }
    }
}
