package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.utils.BotLogger;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;

import java.io.File;
import java.io.IOException;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class FileHandler {

    public static File getLogFile(Guild guild, TextChannel textChannel){

        assert guild != null || textChannel != null;
        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";

        File channelFile = new File(getServerLogFolder(guild) + "/" + channelFolderName);

        if(!doesFileExist(channelFile)){
            try {
                BotLogger.info("Creating new channel folder: " + textChannel.getName() + " on server: " + guild.getName());
                channelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelFile;
    }

    public static File getServerVipFile(Guild guild){

        File serverDataDir = new File(getServerFolder(guild) + "/data/");

        File serverVipFile = new File(serverDataDir + "vip_users.json");

        if (!doesFileExist(serverVipFile)){
            try {
                serverVipFile.createNewFile();
                BotLogger.info("Creating new vip file for server: " + guild.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return serverVipFile;
    }

    /**
     * Helper Methods
     */

    public static File getServerLogFolder(Guild guild){
        String serverFolderName = getServerFolder(guild) + "/channels/";
        File serverFolder = new File(serverFolderName);
        if(!serverFolder.exists()) serverFolder.mkdirs();
        return serverFolder;
    }

    private static File getServerFolder(Guild guild){
        assert guild != null;
        String serverFolderName = guild.getName() + " [" + guild.getId() + "]";
        File serverFolder = new File(getBaseFileDir() + serverFolderName);
        if (!serverFolder.exists()) serverFolder.mkdirs();
        return serverFolder;
    }

    public static String getBaseFileDir(){
        return "discord_servers/";
    }

    private static boolean doesFileExist(File file) {
        if (file != null){
            boolean exists = file.exists();
            return exists;
        }
        return false;
    }
}
