package blume_system.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import blume_system.settings.Config;

public class BossBarController {
	public static Location loc = new Location(Bukkit.getWorld(Config.getWorldName()), 100, 50, 100);
	public static void setBossBarLoc(Location l) {
		loc = l;
	}
	public static Location getBossBarLoc() {
		return loc;
	}
}
