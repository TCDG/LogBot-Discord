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
        try {
        String serverFolderName = "discord_servers/" + guild.getName() + " [" + guild.getId() + "]" + "/channels/";
        File serverFolder = new File(serverFolderName);
        serverFolder.mkdirs();

        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";
        File channelFile = new File(serverFolder + "/" + channelFolderName);


            if (!doesFilerExist(channelFile)) channelFile.createNewFile();
            System.out.println("Making folder for server:" + guild.getName());

        } catch (Exception e) {
            System.out.println("Error here!!!!");
        }
    }

    public static File getLogFile(Guild guild, TextChannel textChannel){

        String channelFolderName = textChannel.getName() + " [" + textChannel.getId() + "].txt";

        File channelFile = new File(getServerFolderName(guild) + "/channels/" + channelFolderName);

        if(!doesFilerExist(channelFile)){
            try {
                channelFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.out.println("Getting log file");
        return channelFile;
    }

    public static File getServerVipFile(Guild guild){

        File serverDataDir = new File(getServerFolderName(guild) + "/data/");
        serverDataDir.mkdirs();

        File serverVipFile = new File(serverDataDir + "/" + "vip_users.json");

        if (!doesFilerExist(serverVipFile)){

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

    private static boolean doesFilerExist(File file) {
        if (file != null){
            boolean exists = file.exists();
            return exists;
        }
        return false;
    }
}
