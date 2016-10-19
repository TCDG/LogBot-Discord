package com.xelitexirish.logbot;

import com.xelitexirish.logbot.commands.*;
import com.xelitexirish.logbot.handlers.BotListener;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.CommandParser;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.OnlineStatus;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class LogBot {

    // https://discordapp.com/oauth2/authorize?client_id=235175172708106240&scope=bot&permissions=0

    public static JDA jda;
    public static final CommandParser parser = new CommandParser();
    public static HashMap<String, ICommand> commands = new HashMap<>();

    public static String DISCORD_TOKEN;
    public static String MAINTAINER_ID;

    public static void main(String[] args){

        if(args.length == 2){
            DISCORD_TOKEN = args[0];
            MAINTAINER_ID = args[1];
        }else {
            BotLogger.error("Please enter a valid discord token and maintainer ID and try again!");
            return;
        }

        try {
            jda = new JDABuilder().setBotToken(DISCORD_TOKEN).setAutoReconnect(true).addListener(new BotListener()).buildBlocking();
        }catch (Exception e){
            e.printStackTrace();
        }

        registerCommands();
        handlePlayingMessage();

        BotLogger.info("Started Logging...");
    }

    private static void registerCommands(){
        commands.put("vip", new VipCommand());
        commands.put("help", new HelpCommand());
        commands.put("get", new GetCommand());
        commands.put("status", new StatusCommand());
    }

    private static void handlePlayingMessage(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String[] messages = {"Currently logging: " + getTotalMembers() + " members!", "I'm currently logging " + jda.getGuilds().size() + " servers!"};
                jda.getAccountManager().setGame(messages[new Random().nextInt(messages.length)]);
            }
        }, 15000);
    }

    /**
     * Helper Methods
     */

    public static void handleCommand(CommandParser.CommandContainer cmd){
        if(commands.containsKey(cmd.invoke)){
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe){
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }

    public static int getTotalMembers() {
        int totalMembers = 0;
        for (Guild guild : jda.getGuilds()){
            totalMembers =+ guild.getUsers().size();
        }

        return totalMembers;
    }

    public static int getTotalChannels(){
        int totalChannels = 0;
        for (Guild guild : jda.getGuilds()){
            for (TextChannel channel : guild.getTextChannels()){
                totalChannels++;
            }
        }
        return totalChannels;
    }

    public static void toggleOnlineStatus(){
        if (jda.getSelfInfo().getOnlineStatus() == OnlineStatus.ONLINE) {
            jda.getAccountManager().setStatus(OnlineStatus.OFFLINE);
        }else {
            jda.getAccountManager().setStatus(OnlineStatus.INVISIBLE);
        }
    }
}
