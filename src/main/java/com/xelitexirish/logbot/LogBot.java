package com.xelitexirish.logbot;

import com.xelitexirish.logbot.commands.ICommand;
import com.xelitexirish.logbot.commands.VipCommand;
import com.xelitexirish.logbot.handlers.BotListener;
import com.xelitexirish.logbot.utils.CommandParser;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

import java.util.HashMap;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class LogBot {

    // https://discordapp.com/oauth2/authorize?client_id=235175172708106240&scope=bot&permissions=0

    public static JDA jda;
    public static final CommandParser parser = new CommandParser();
    public static HashMap<String, ICommand> commands = new HashMap<>();

    public static String DISCORD_TOKEN;

    public static void main(String[] args){

        if(args.length > 0){
            DISCORD_TOKEN = args[0];
        }else {
            System.out.println("Please enter a valid discord token and try again.");
            return;
        }

        try {
            jda = new JDABuilder().setBotToken(DISCORD_TOKEN).setAutoReconnect(true).addListener(new BotListener()).buildBlocking();
            jda.getAccountManager().setGame("Logging everything...");
        }catch (Exception e){
            e.printStackTrace();
        }

        registerCommands();

        System.out.println("Logging...");
    }

    private static void registerCommands(){
        commands.put("vip", new VipCommand());
    }

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
}
