package it.mitl.vpnutils;

import it.mitl.vpnutils.Listeners.OnJoin;
import it.mitl.vpnutils.utils.APIChanges;
import org.bukkit.plugin.java.JavaPlugin;

public final class VPNUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Check if the API version is still supported.
        if (!APIChanges.versionSupported(this)) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register Events
        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
