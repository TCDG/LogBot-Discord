package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.events.guild.GuildJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberBanEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.exceptions.PermissionException;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.utils.InviteUtil;

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
                stringBuilder.append("I am currently logging the following " + event.getJDA().getGuilds().size() + " servers:\n");
                for (Guild guild : event.getJDA().getGuilds()) {
                    InviteUtil.AdvancedInvite invite = null;
                    try {
                        if (guild.getInvites().size() <= 0) return;
                        invite = guild.getInvites().get(0);
                    } catch (PermissionException e) {}
                    stringBuilder.append("\t-" + guild.getName() + invite + "\n");
                }
                event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(stringBuilder.toString()));

            } else {
                event.getAuthor().getPrivateChannel().sendMessage("Sorry but I can't recognise any command or input you make unless it's made in a guild channel! I need to make sure you have permission!");
            }
        }

        DiscordLogHandler.onMessageRecieved(event);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        DiscordLogHandler.onGuildJoin(event);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        DiscordLogHandler.onMemberJoin(event);

        if (event.getUser().getId().equals(LogBot.jda.getSelfInfo().getId())) {
            BotLogger.info("I joined the server: " + event.getGuild());
        }
    }

    @Override
    public void onGuildMemberBan(GuildMemberBanEvent event) {

        DiscordLogHandler.onMemberBan(event);
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event) {

        DiscordLogHandler.onMessageDelete(event);
    }

    @Override
    public void onTextChannelDelete(TextChannelDeleteEvent event) {

        DiscordLogHandler.onTextChannelDelete(event);
    }

    @Override
    public void onTextChannelCreate(TextChannelCreateEvent event) {

        DiscordLogHandler.onTextChannelCreated(event);
    }

    @Override
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {

        DiscordLogHandler.onVoiceChannelDelete(event);
    }

    @Override
    public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {

        DiscordLogHandler.onVoiceChannelCreate(event);
    }

    @Override
    public void onGuildMemberNickChange(GuildMemberNickChangeEvent event) {
        DiscordLogHandler.onUsernameUpdate(event);
    }
}
