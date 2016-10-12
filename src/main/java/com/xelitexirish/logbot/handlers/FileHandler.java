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
        File channelFile = new File(serverFolder + "/channels/" + channelFolderName);

        try {
            if (!doesFilerExist(channelFile))
            channelFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getLogFile(Guild guild, TextChannel textChannel){

        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";

        File channelFile = new File(getServerFolderName(guild) + "/channels/" + channelFolderName);

        if(doesFilerExist(channelFile)) return channelFile;
        else {
            try {
                channelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return channelFile;
    }

    public static File getServerVipFile(Guild guild){
        String serverFolderName = guild.getName() + " [" + guild.getId() + "]";

        File serverVipFile = new File("discord_servers/" + serverFolderName + "/data/" + "vipUsers.json");
        if (doesFilerExist(serverVipFile)) return serverVipFile;
        else{
            try {
                serverVipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return serverVipFile;
    }

    /**
     * Helper Methods
     */

    private static String getServerFolderName(Guild guild){
        String serverFolderName = guild.getName() + " [" + guild.getId() + "]";
        return getBaseFileDir() + serverFolderName;
    }

    public static String getBaseFileDir(){
        return "discord_servers/";
    }

    public static boolean doesChannelFileExist(Guild guild, TextChannel channel){
        return doesFilerExist(getLogFile(guild, channel));
    }

    private static boolean doesFilerExist(File file){
        if(file == null) return false;
        return file.exists();
    }
}
