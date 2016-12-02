package kingdgrizzle.logbot.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import kingdgrizzle.logbot.utils.BotLogger;
import kingdgrizzle.logbot.utils.GeneralUtils;

public class FileHandler {

	public static File getLogFile(Guild guild, TextChannel textChannel) {
		File serverDataFolder = getServerLogFolder(guild);
		File channelFile = null;
		
		for (File files : serverDataFolder.listFiles()) {
			String[] fileName = files.getName().split("-");
			if (fileName.length > 2 && fileName[1].contains(textChannel.getId())) {
				channelFile = files;
			}
		}
		
		String channelFolderName = textChannel.getName() + "-[" + textChannel.getId() + "].txt";
		if (channelFile == null) channelFile = new File(serverDataFolder + "/" + channelFolderName);
		
		if (!doesFileExist(channelFile)) {
			try {
				BotLogger.info("Creating new channel folder: " + textChannel.getName() + " on server: " + guild.getName());
				channelFile.createNewFile();
			} catch (IOException e) {
                BotLogger.debug("Wasn't able to create the server log file for the server: " + guild.getName(), e);
            }
		}
		
		return channelFile;
	}
	
	 public static File getServerVipFile(Guild guild) {

	        File serverVipFile = new File(getServerDataFolder(guild) + "/" + "vip_users.json");

	        if (!doesFileExist(serverVipFile)) {
	            try {
	                serverVipFile.createNewFile();
	                BotLogger.info("Creating new vip file for server: " + guild.getName());
	            } catch (IOException e) {
	                BotLogger.debug("Wasn't able to create the vip file for the server: " + guild.getName(), e);
	            }
	        }
	        return serverVipFile;
	    }
	 
	 public static File getConfigFile() {
		 File configFile = new File("config.json");
		 
		 if (!doesFileExist(configFile)) {
			 try {
				 configFile.createNewFile();
				 ConfigHandler.writeConfigFile();
				 BotLogger.info("Creating Config File!");
			 } catch (IOException e) {
				 BotLogger.debug("Wasn't able to create config file!", e);
			 }
		 }
		 return configFile;
	 }

	    public static File getServerEventLogFile(Guild guild) {

	        File serverEventLogFile = new File(getServerDataFolder(guild) + "/" + "event_log.txt");

	        if (!doesFileExist(serverEventLogFile)) {
	            try {
	                serverEventLogFile.createNewFile();
	                BotLogger.info("Creating new event log file for the server: " + guild.getName());
	            } catch (IOException e) {
	                BotLogger.debug("Wasn't able to create the event log file for the server: " + guild.getName());
	            }
	        }
	        return serverEventLogFile;
	    }

	    public static File getTempLogFile(MessageReceivedEvent event, User user, int searchLength) {
	        String tempFileName = user.getName() + "-[" + user.getId() + "].txt";

	        File tempLogFile = new File(getTempFolder() + "/" + tempFileName);
	        if (doesFileExist(tempLogFile)) tempLogFile.delete();
	        if (!doesFileExist(tempLogFile)) {
	            try {
	                tempLogFile.createNewFile();
	                BotLogger.info("Creating new log file for user: " + user.getName());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        DiscordLogHandler.writePlayerDataLog(event, tempLogFile, user, searchLength);
	        return tempLogFile;
	    }

	    /**
	     * Helper Methods
	     */

	    public static File[] getAllServerLogFiles(Guild guild) {
	        String serverFolderName = getServerFolder(guild) + "/channels/";
	        File serverFolder = new File(serverFolderName);
	        return serverFolder.listFiles();
	    }

	    public static File getServerLogFolder(Guild guild) {
	        String serverFolderName = getServerFolder(guild) + "/channels/";
	        File serverFolder = new File(serverFolderName);
	        if (!serverFolder.exists()) serverFolder.mkdirs();
	        return serverFolder;
	    }

	    public static File getServerDataFolder(Guild guild) {
	        String serverFolderName = getServerFolder(guild) + "/data/";
	        File serverFolder = new File(serverFolderName);
	        if (!serverFolder.exists()) serverFolder.mkdirs();
	        return serverFolder;
	    }

	    private static File getServerFolder(Guild guild) {
	        assert guild != null;
	        File baseFileDir = new File(getBaseFileDir());
	        File serverFolder = null;

	        if (!doesFileExist(baseFileDir)) baseFileDir.mkdirs();

	        for (File file : baseFileDir.listFiles()) {
	            String[] nameSplit = file.getName().split("-");
	            if (nameSplit.length > 2 && nameSplit[1].contains(guild.getId())) {
	                serverFolder = file;
	            }
	        }

	        String serverFolderName = guild.getName() + " -[" + guild.getId() + "]";
	        if (serverFolder == null) serverFolder = new File(getBaseFileDir() + serverFolderName);

	        if (!serverFolder.exists()) serverFolder.mkdirs();
	        return serverFolder;
	    }

	    public static File getTempFolder() {
	        File tempFolder = new File(getBaseFileDir() + "temp/");
	        if (!doesFileExist(tempFolder)) tempFolder.mkdirs();
	        return tempFolder;
	    }
	    
	    public static boolean removeTempFolder() {
	    	File tempFolder = new File(getBaseFileDir() + "temp/");
	    	return tempFolder.delete();
	    }

	    public static ArrayList<File> getAllLogFiles() {
	        return GeneralUtils.listDirectoryFiles(getBaseFileDir(), 0);
	    }

	    public static String getBaseFileDir() {
	        return "discord_servers/";
	    }

	    public static boolean doesFileExist(File file) {
	        return file != null && file.exists();
	    }
}
