package com.xelitexirish.logbot;

import com.xelitexirish.logbot.handlers.BotListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class LogBot {

    public static JDA jda;

    public static boolean LOGIN_AS_USER = false;
    public static String DISCORD_TOKEN;

    public static void main(String[] args){

        if(args.length > 0){
            LOGIN_AS_USER = Boolean.parseBoolean(args[0]);
            DISCORD_TOKEN = args[1];
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
    }
}
