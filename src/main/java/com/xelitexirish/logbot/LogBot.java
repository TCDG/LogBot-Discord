package com.xelitexirish.logbot;

import com.xelitexirish.logbot.handlers.BotListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class LogBot {

    // https://discordapp.com/oauth2/authorize?client_id=235175172708106240&scope=bot&permissions=0

    public static JDA jda;

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
        System.out.println("Logging...");
    }
}
