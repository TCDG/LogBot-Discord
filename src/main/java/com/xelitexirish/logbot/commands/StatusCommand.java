package com.xelitexirish.logbot.commands;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 15/10/2016. www.xelitexirish.com
 */
public class StatusCommand implements ICommand{

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(getStatusText(), "css"));
    }

    @Override
    public String help() {
        return "Displays the basic status information for the bot";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "status";
    }

    private String getStatusText(){
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendString("{LotBot Status}\n");
        messageBuilder.appendString("Users: " + LogBot.getTotalMembers() + "\n");
        messageBuilder.appendString("Servers: " + LogBot.jda.getGuilds().size());
        return messageBuilder.toString();
    }
}
