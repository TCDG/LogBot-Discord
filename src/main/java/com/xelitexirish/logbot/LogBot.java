package com.xelitexirish.logbot;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import com.xelitexirish.logbot.commands.GetCommand;
import com.xelitexirish.logbot.commands.ICommand;
import com.xelitexirish.logbot.commands.NewHelpCommand;
import com.xelitexirish.logbot.commands.PurgeCommand;
import com.xelitexirish.logbot.commands.StatusCommand;
import com.xelitexirish.logbot.commands.VIPCommand;
import com.xelitexirish.logbot.handlers.BotListener;
import com.xelitexirish.logbot.handlers.ConfigHandler;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.CommandParser;

public class LogBot {

	public static JDA jda;
	public static final CommandParser parser = new CommandParser();
	public static HashMap<String, ICommand> commands = new HashMap<>();

	public static String DISCORD_TOKEN = "";
	public static String MAINTAINER_ID = "";
    public static boolean ENABLE_DEBUG_MODE;
    
	public static void main(String[] args) {
		
		DISCORD_TOKEN = ConfigHandler.isToken();
		MAINTAINER_ID = ConfigHandler.isMaintainer();
		ENABLE_DEBUG_MODE = ConfigHandler.isDebugMode();
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(DISCORD_TOKEN).setAutoReconnect(true).addListener(new BotListener()).buildBlocking();
		} catch (Exception e) {
			BotLogger.error("Please open config.json and insert your token and Maintainer ID and try again!");
			System.exit(0);
		}
		registerCommands();
        handlePlayingMessage();
        
        BotLogger.info("Started Logging...");
	}
	
	private static void registerCommands() {
        commands.put("vip", new VIPCommand());
        commands.put("get", new GetCommand());
        commands.put("status", new StatusCommand());
        commands.put("purge", new PurgeCommand());
        commands.put("help", new NewHelpCommand());
    }
	
	private static void handlePlayingMessage() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String[] messages = {"Currently logging: " + jda.getUsers().size() + " members!", "I'm currently logging " + jda.getGuilds().size() + " servers!"};
                jda.getPresence().setGame(Game.of(messages[new Random().nextInt(messages.length)]));
            }
        }, 15000);
    }
	
	/**
     * Helper Methods
     */

    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            commands.get(cmd.invoke).action(cmd.args, cmd.event);
            commands.get(cmd.invoke).executed(true, cmd.event);
        }
    }

    public static int getTotalChannels() {
        int totalChannels = 0;
        for (Guild guild : jda.getGuilds()){
            for (TextChannel channel : guild.getTextChannels()){
                totalChannels++;
            }
        }
        return totalChannels;
    }
    
    public static void setOnlineStatus() {
    	if (jda.getPresence().getStatus() == OnlineStatus.OFFLINE || jda.getPresence().getStatus() == OnlineStatus.INVISIBLE) {
    		jda.getPresence().setStatus(OnlineStatus.ONLINE);
    	}
    }
    
    public static void setOfflineStatus() {
    	if (jda.getPresence().getStatus() == OnlineStatus.ONLINE) {
    		jda.getPresence().setStatus(OnlineStatus.OFFLINE);
    	}
    }
}
