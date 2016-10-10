package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;

import java.io.File;
import java.io.IOException;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class FileHandler {

    public static void createNewChannel(Guild guild, TextChannel textChannel){
        String serverFolderName = "discord_servers/" + guild.getName() + " [" + guild.getId() + "]";
        File serverFolder = new File(serverFolderName);
        serverFolder.mkdirs();

        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";
        File channelFile = new File(serverFolder + "/" + channelFolderName);

        try {
            if (!doesFilerExist(channelFile))
            channelFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getLogFile(Guild guild, TextChannel textChannel){
        String serverFolderName = guild.getName() + " [" + guild.getId() + "]";
        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";

        File channelFile = new File("discord_servers/" + serverFolderName + "/" + channelFolderName);

        if(doesFilerExist(channelFile)) return channelFile;
        else return null;
    }

    private static boolean doesFilerExist(File file){
        return file.exists();
    }
}
