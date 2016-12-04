package com.xelitexirish.logbot.utils;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

public class MessageUtils {
	
	@Deprecated
    public static Message getNoPermissionMsgOld(Permission permission) {
        return MessageUtils.wrapStringInCodeBlock("Sorry but you don't have the required permission to use this command. Required permission: " + permission.name());
    }
    
    public static MessageEmbed getNoPermissionMsg(Permission permission) {
    	EmbedBuilder eb = new EmbedBuilder();
    	eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
    	eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
    	eb.setColor(Color.red);
    	eb.setTitle("Error, you don't have the required permission!");
    	eb.setDescription("You need the permission " + permission.name() + " to run that command!");
    	MessageEmbed embed = eb.build();
    	return embed;
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
