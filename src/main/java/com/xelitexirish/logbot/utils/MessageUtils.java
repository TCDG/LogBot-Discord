package com.xelitexirish.logbot.utils;

import net.dv8tion.jda.Permission;

/**
 * Created by XeliteXirish on 12/10/2016. www.xelitexirish.com
 */
public class MessageUtils {

    public static String getNoPermissionMsg(Permission permission) {
        return MessageUtils.wrapStringInCodeBlock("Sorry but you don't have the required permission to use this command. Required permission: " + permission.name());
    }

    public static String wrapStringInCodeBlock(String message) {
        String newMessage = "```" + message + "```";
        return newMessage;
    }
}
