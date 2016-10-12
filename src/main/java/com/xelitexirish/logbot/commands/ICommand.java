package com.xelitexirish.logbot.commands;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 12/10/2016. www.xelitexirish.com
 */
public interface ICommand {

    boolean called(String[] args, MessageReceivedEvent event);

    void action(String[] args, MessageReceivedEvent event);

    String help();

    void executed(boolean success, MessageReceivedEvent event);

    String getTag();
}
