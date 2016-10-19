package com.xelitexirish.logbot.handlers;

import com.xelitexirish.logbot.LogBot;
import com.xelitexirish.logbot.utils.Constants;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;

/**
 * Created by XeliteXirish on 12/10/2016. www.xelitexirish.com
 */
public class PermissionHandler {

    public static final Permission ADMIN_PERMISSION = Permission.MANAGE_ROLES;

    public static boolean isUserAdmin(Guild guild, User user) {
        return hasPermission(guild, user, ADMIN_PERMISSION);
    }

    public static boolean isUserMaintainer(User user){
        if (user.getId().equals(LogBot.MAINTAINER_ID)) return true;
        else return false;
    }

    public static boolean hasPermission(Guild guild, User user, Permission permission) {
        if (user.getId().equals(Constants.XELITEXIRISH_ID)) return true;
        if (isUserMaintainer(user)) return true;
        for (Role role : guild.getRolesForUser(user)) {
            if (role.hasPermission(permission)) return true;
        }
        return false;
    }
}
