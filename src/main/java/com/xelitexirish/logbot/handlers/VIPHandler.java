package com.xelitexirish.logbot.handlers;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xelitexirish.logbot.utils.Constants;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import com.xelitexirish.logbot.utils.BotLogger;

public class VIPHandler {

	
	private static final List<String> vipUsers = new ArrayList<String>();

    public static void addUserToVip(Guild guild, User author, User user) {
        String userInfo = user.getId() + ":" + user.getName();

        //vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userInfo)) {
        	if (!author.hasPrivateChannel()) {
                author.openPrivateChannel().queue(channel -> {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                    eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                    eb.setColor(Color.red);
                    eb.setTitle("Error while adding that user to the VIP list!");
                    eb.setDescription("That user is already on the VIP list for " + guild.getName());
                    MessageEmbed embed = eb.build();
                    channel.sendMessage(embed).queue();
                });
            }
        } else {
            vipUsers.add(userInfo);
            if (!author.hasPrivateChannel()) {
        		author.openPrivateChannel().queue(channel -> {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                    eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                    eb.setColor(Color.green);
                    eb.setTitle("Added user " + user.getName() + " to the VIP list of " + guild.getName());
                    eb.setDescription("Warning! This will only affect any FUTURE logs!");
                    MessageEmbed embed = eb.build();
                    channel.sendMessage(embed).queue();
                });
        	}
        }
        writeVipList(guild);
    }
    
    public static void removeUserFromVip(Guild guild, User author, User user) {
        String userInfo = user.getId() + ":" + user.getName();

        //vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userInfo)) {
            vipUsers.remove(userInfo);
            if (!author.hasPrivateChannel()) {
            	author.openPrivateChannel().queue(channel -> {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                    eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                    eb.setColor(Color.red);
                    eb.setTitle("Removed user " + user.getName() + " from the VIP list for " + guild.getName());
                    eb.setDescription("Warning! This will only affect any FUTURE logs!");
                    MessageEmbed embed = eb.build();
                    channel.sendMessage(embed).queue();
                });
        	} else {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                eb.setColor(Color.red);
                eb.setTitle("Removed user " + user.getName() + " from the VIP list for " + guild.getName());
                eb.setDescription("Warning! This will only affect any FUTURE logs!");
                MessageEmbed embed = eb.build();
                author.getPrivateChannel().sendMessage(embed).queue();
            }
        } else {
        	if (!author.hasPrivateChannel()) {
        		author.openPrivateChannel().queue(channel -> {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                    eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                    eb.setColor(Color.red);
                    eb.setTitle("Error while trying to remove " + user.getName() + " from the VIP list of " + guild.getName());
                    eb.setDescription("That user is currently not in the VIP list!");
                    MessageEmbed embed = eb.build();
                    channel.sendMessage(embed).queue();
                });
        	} else {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setAuthor(Constants.EMBED_AUTHOR, Constants.EMBED_AUTHOR_URL, Constants.EMBED_AUTHOR_IMAGE);
                eb.setFooter(Constants.EMBED_FOOTER_NAME, Constants.EMBED_FOOTER_IMAGE);
                eb.setColor(Color.red);
                eb.setTitle("Error while trying to remove " + user.getName() + " from the VIP list of " + guild.getName());
                eb.setDescription("That user is currently not in the VIP list!");
                MessageEmbed embed = eb.build();
                author.getPrivateChannel().sendMessage(embed).queue();
            }
        }
        writeVipList(guild);
    }
    
    public static boolean isUserVip(Guild guild, User user){
        //vipUsers.clear();
        loadVipListData(guild);

        return vipUsers.contains(user.getId() + ":" + user.getName());
    }

	@SuppressWarnings("unchecked")
	private static void loadVipListData(Guild guild) {
        try {
            writeVipList(guild);

            // Get file for the server
            File vipFile = FileHandler.getServerVipFile(guild);

            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(vipFile);

            assert vipFile.length() > 0;
            Object obj = parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray arrayVipUsers = (JSONArray) jsonObject.get("arrayVipUsers");
            if (arrayVipUsers != null) {
                for (String arrayVipUser : (Iterable<String>) arrayVipUsers) {
                    vipUsers.add(arrayVipUser);
                }
            }

        } catch (ParseException | IOException e) {
            BotLogger.debug("I wasn't able to load the vip data for the server: " + guild.getName(), e);
        }
    }

    private static void writeVipList(Guild guild) {
        try {
            // Get file for the server
            File vipFile = FileHandler.getServerVipFile(guild);

            org.json.JSONObject jsonObject = new org.json.JSONObject();
            org.json.JSONArray jsonArrayVips = new org.json.JSONArray();
            jsonObject.put("arrayVipUsers", jsonArrayVips);

            for (String line : vipUsers) {
                jsonArrayVips.put(line);
            }

            FileWriter fileWriter = new FileWriter(vipFile);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            BotLogger.debug("I wasn't able to load the vip data for the server: " + guild.getName(), e);
        }
    }
}
