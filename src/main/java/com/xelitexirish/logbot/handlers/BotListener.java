package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.Constants;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        DiscordLogHandler.onMessageRecieved(event);

        // Commands
        if (event.getMessage().getContent().startsWith(Constants.COMMAND_PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
            LogBot.handleCommand(LogBot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
        }
    }
}
