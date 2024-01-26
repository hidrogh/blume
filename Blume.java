package fr.hidro.blume;

import fr.hidro.blume.general.DisconnectMessages;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blume extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Blume initialized.");
        getLogger().info("  Version: 1.0");
        getLogger().info("  Paper 1.20.4");
        DisconnectMessages.kickOnReload();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
