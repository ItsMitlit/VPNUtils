package it.mitl.vpnutils.Listeners;

import it.mitl.vpnutils.utils.APIRequests;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnJoin implements Listener {

    private final JavaPlugin plugin;

    public OnJoin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {

        FileConfiguration config = plugin.getConfig();

        boolean isVPN = APIRequests.checkVPN(event.getRealAddress().toString());
        if (config.getBoolean("blockVPNs")) {
            if (isVPN) event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "VPN usage is not allowed on this server. Please disable your VPN and try again.");
        }
    }

}
