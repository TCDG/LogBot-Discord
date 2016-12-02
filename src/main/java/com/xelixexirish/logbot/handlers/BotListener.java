package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import com.xelitexirish.logbot.utils.BotLogger;
import com.xelitexirish.logbot.utils.Constants;
import com.xelitexirish.logbot.utils.MessageUtils;

public class BotListener extends ListenerAdapter {

	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Commands
		Message msg = event.getMessage();
        if (msg.getContent().startsWith(Constants.COMMAND_PREFIX) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            LogBot.handleCommand(LogBot.parser.parse(msg.getContent().toLowerCase(), event));
        }

        if (!event.getAuthor().isBot() && event.getMessage().getChannelType().equals(ChannelType.PRIVATE)) {
            if (event.getMessage().getContent().equalsIgnoreCase("servers")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("I am currently logging the following " + event.getJDA().getGuilds().size() + " servers:\n");
                for (Guild guild : event.getJDA().getGuilds()) {
                    stringBuilder.append("\t-" + guild.getName() + "\n");
                }
                try {
					event.getAuthor().getPrivateChannel().sendMessage(MessageUtils.wrapStringInCodeBlock(stringBuilder.toString())).block();
				} catch (RateLimitedException e) {
					e.printStackTrace();
				}
                BotLogger.debug(stringBuilder.toString());

            } else {
                event.getAuthor().getPrivateChannel().sendMessage("Sorry but I can't recognise any command or input you make unless it's made in a guild channel! I need to make sure you have permission!").queue();
            }
        }

        DiscordLogHandler.onMessageReceived(event);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        DiscordLogHandler.onMemberJoin(event);

        if (event.getMember().getUser().getId().equals(LogBot.jda.getSelfUser().getId())) {
            BotLogger.info("I joined the server: " + event.getGuild());
        }
    }

    @Override
    public void onGuildBan(GuildBanEvent event) {
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
