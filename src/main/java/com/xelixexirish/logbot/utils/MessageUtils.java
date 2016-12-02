package com.xelitexirish.logbot.utils;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class MessageUtils {
	
    public static Message getNoPermissionMsg(Permission permission) {
        return MessageUtils.wrapStringInCodeBlock("Sorry but you don't have the required permission to use this command. Required permission: " + permission.name());
    }

    public static Message wrapStringInCodeBlock(String message) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendCodeBlock(message, "");
        return messageBuilder.build();
    }

    public static Message wrapStringInCodeBlock(String message, String language) {
       MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendCodeBlock(message, language);
        return messageBuilder.build();
    }
    
    public static Message usefulError(User user, String message) {
    	MessageBuilder messageBuilder = new MessageBuilder();
    	messageBuilder.appendMention(user).appendString(", you haven\'t used the correct syntax. Please use: \n").appendCodeBlock(message, "");
    	return messageBuilder.build();
    }
}
