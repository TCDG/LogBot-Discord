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

    private static File vipUsersFile = new File("/data/vip_users.json");
    private static final List<String> vipUsers = new ArrayList<String>();

    public static void init(Guild guild) {
        try {
            if (!vipUsersFile.exists()) vipUsersFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        vipUsers.clear();
        loadVipListData();
    }

    public static void addUserToVip(Guild guild, User author, User vip) {
        String userId = vip.getId();

        vipUsers.clear();
        loadVipListData();

        if (vipUsers.contains(userId)) {
            author.getPrivateChannel().sendMessage("That user is already on the VIP list for this server.");
        } else {
            vipUsers.add(userId);
            author.getPrivateChannel().sendMessage(vip.getUsername() + " is now added to the vip list, this will only effect FUTURE logs.");
        }
        writeVipList();
    }

    public static void removeUserFromVip(User author, User vip) {
        String userId = vip.getId();

        vipUsers.clear();
        loadVipListData();

        if (vipUsers.contains(userId)) {
            author.getPrivateChannel().sendMessage(vip.getUsername() + " is now removed from the vip list, this will only effect FUTURE logs.");
        } else {
            author.getPrivateChannel().sendMessage("That user is currently not on the VIP list.");
        }
        writeVipList();
    }

    private static void loadVipListData() {
        try {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader(vipUsersFile));
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

    private static void writeVipList() {
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject();
            org.json.JSONArray jsonArrayVips = new org.json.JSONArray();
            jsonObject.put("arrayVipUsers", jsonArrayVips);

            for (String line : vipUsers) {
                jsonArrayVips.put(line);
            }

            FileWriter fileWriter = new FileWriter(vipUsersFile);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
