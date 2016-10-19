package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by XeliteXirish on 10/10/2016. www.xelitexirish.com
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Commands
        if (event.getMessage().getContent().startsWith(Constants.COMMAND_PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
            LogBot.handleCommand(LogBot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
        }

        if (!event.getAuthor().isBot() && event.getMessage().isPrivate()) {
            if (event.getMessage().getContent().equalsIgnoreCase("servers")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("I am currently logging the following servers:\n");
                for (Guild guild : event.getJDA().getGuilds()){
                    stringBuilder.append("\t-" + guild.getName() + "\n");
                }
                event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(stringBuilder.toString()));

            } else {
                event.getAuthor().getPrivateChannel().sendMessage("Sorry but I can't recognise any command or input you make unless it's made in a guild channel! I need to make sure you have permission!");
            }
        }

        DiscordLogHandler.onMessageRecieved(event);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if (event.getUser().getId().equals(LogBot.jda.getSelfInfo().getId())) {
            BotLogger.info("I joined the server: " + event.getGuild());
        }
    }
}
