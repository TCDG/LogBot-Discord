package kingdgrizzle.logbot.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import kingdgrizzle.logbot.utils.BotLogger;

public class VIPHandler {

	
	private static final List<String> vipUsers = new ArrayList<String>();

    public static void addUserToVip(Guild guild, User author, User user) {
    	user.openPrivateChannel();
        String userInfo = user.getId() + ":" + user.getName();

        //vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userInfo)) {
            author.getPrivateChannel().sendMessage("That user is already on the VIP list for this server.").queue();
        } else {
            vipUsers.add(userInfo);
            author.getPrivateChannel().sendMessage(user.getName() + " is now added to the VIP list, this will only effect FUTURE logs.").queue();
        }
        writeVipList(guild);
    }
    
    public static void removeUserFromVip(Guild guild, User author, User user) {
    	user.openPrivateChannel();
        String userInfo = user.getId() + ":" + user.getName();

        //vipUsers.clear();
        loadVipListData(guild);

        if (vipUsers.contains(userInfo)) {
            vipUsers.remove(userInfo);
            author.getPrivateChannel().sendMessage(user.getName() + " is now removed from the VIP list, this will only effect FUTURE logs.").queue();
        } else {
            author.getPrivateChannel().sendMessage("That user is currently not on the VIP list.").queue();
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
