package it.mitl.vpnutils.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class APIChanges {

    public static boolean versionSupported(JavaPlugin plugin) {
        try {
            int APIStatus = APIRequests.getAPIVersionInfo();

            if (APIStatus == 1) {
                return true;
            } else if (APIStatus == 2) {
                plugin.getLogger().severe("v1 API support is ending. Please check the plugin page for updates. (https://modrinth.com/project/vpnutils)");
                return true;
            } else if (APIStatus == 3) {
                plugin.getLogger().severe("This plugin is using an outdated API version. Please check the plugin page for updates. (https://modrinth.com/project/vpnutils)");
                return false;

            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }
}
