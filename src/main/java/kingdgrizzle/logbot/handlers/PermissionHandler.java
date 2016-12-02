package kingdgrizzle.logbot.handlers;

import kingdgrizzle.logbot.LogBot;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import kingdgrizzle.logbot.utils.Constants;

public class PermissionHandler {
	
	public static final Permission ADMIN_PERMISSION = Permission.MESSAGE_MANAGE;

    public static boolean isUserAdmin(Guild guild, User user) {
        return hasPermission(guild, user, ADMIN_PERMISSION);
    }

    public static boolean isUserMaintainer(User user){
        if (user.getId().equals(LogBot.MAINTAINER_ID)) return true;
        else return false;
    }

    public static boolean hasPermission(Guild guild, User user, Permission permission) {
        if (user.getId().equals(Constants.XELITEXIRISH_ID) || user.getId().equals(Constants.KINGDGRIZZLE_ID)) return true;
        if (isUserMaintainer(user)) return true;
        for (Role role : guild.getMember(user).getRoles()) {
            if (role.hasPermission(permission)) return true;
        }
        return false;
    }
}
