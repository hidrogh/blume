package blume_system.config;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class DisconnectMessages {
    public static void kickOnReload() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (!p.isOp()) {
                p.kickPlayer(ChatColor.WHITE + p.getName() + ", you were kicked due to a system reboot. You can rejoin shortly.");
            }
        }
    }
}
