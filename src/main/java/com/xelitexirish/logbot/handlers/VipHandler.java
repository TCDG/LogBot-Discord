package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XeliteXirish on 11/10/2016. www.xelitexirish.com
 */
public class VipHandler {

    private static final List<String> vipUsers = new ArrayList<String>();

    public static void addUserToVip(Guild guild, User author, User vip) {
        String userId = vip.getId();

        vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userId)) {
            author.getPrivateChannel().sendMessage("That user is already on the VIP list for this server.");
        } else {
            vipUsers.add(userId);
            author.getPrivateChannel().sendMessage(vip.getUsername() + " is now added to the vip list, this will only effect FUTURE logs.");
        }
        writeVipList(guild);
    }

    public static void removeUserFromVip(Guild guild, User author, User vip) {
        String userId = vip.getId();

        vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userId)) {
            author.getPrivateChannel().sendMessage(vip.getUsername() + " is now removed from the vip list, this will only effect FUTURE logs.");
        } else {
            author.getPrivateChannel().sendMessage("That user is currently not on the VIP list.");
        }
        writeVipList(guild);
    }

    public static boolean isUserVip(Guild guild, User user){
        vipUsers.clear();
        loadVipListData(guild);

        return vipUsers.contains(user.getId());
    }

    private static void loadVipListData(Guild guild) {
        try {
            // Get file for the server

            File vipFile = FileHandler.getServerVipFile(guild);

            JSONParser parser = new JSONParser();

            assert vipFile != null;
            Object obj = parser.parse(new FileReader(vipFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray arrayVipUsers = (JSONArray) jsonObject.get("arrayVipUsers");
            if (arrayVipUsers != null) {
                Iterator<String> iterator = arrayVipUsers.iterator();
                while (iterator.hasNext()) {
                    vipUsers.add(iterator.next());
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
