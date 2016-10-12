package com.xelitexirish.logbot.handlers;

import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;

/**
 * Created by XeliteXirish on 12/10/2016. www.xelitexirish.com
 */
public class PermissionHandler {

    public static final Permission ADMIN_PERMISSION = Permission.ADMINISTRATOR;

    public static boolean isUserAdmin(Guild guild, User user){
        return hasPermission(guild, user, Permission.ADMINISTRATOR);
    }

    public static boolean hasPermission(Guild guild, User user, Permission permission){
        for (Role role : guild.getRolesForUser(user)){
            if (role.hasPermission(permission)) return true;
        }
        return false;
    }
}
