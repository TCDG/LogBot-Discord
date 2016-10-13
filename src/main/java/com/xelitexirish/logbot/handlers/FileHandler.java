package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;

import java.io.File;
import java.io.IOException;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class FileHandler {

    public static File getLogFile(Guild guild, TextChannel textChannel){

        File serverLogFolder = getServerLogFolder(guild);

        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";
        File channelFile = new File(serverLogFolder + "/" + channelFolderName);

        if(!doesFileExist(channelFile)){
            try {
                System.out.println("Creating new channel folder: " + textChannel.getName() + " on server: " + guild.getName());
                channelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelFile;
    }

    public static File getServerVipFile(Guild guild){

        File serverDataDir = new File(getServerFolderName(guild) + "/data/");
        serverDataDir.mkdirs();

        File serverVipFile = new File(serverDataDir + "/" + "vip_users.json");

        if (!doesFileExist(serverVipFile)){

            try {
                serverVipFile.createNewFile();
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
        String serverFolderName = getServerFolderName(guild) + "/channels/";
        File serverFolder = new File(serverFolderName);
        if(!serverFolder.exists()) serverFolder.mkdirs();
        return serverFolder;
    }

    private static String getServerFolderName(Guild guild){
        String serverFolderName = guild.getName() + " [" + guild.getId() + "]";
        return getBaseFileDir() + serverFolderName;
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
