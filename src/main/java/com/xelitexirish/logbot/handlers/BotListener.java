package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        DiscordLogHandler.onMessageRecieved(event);
    }
}
