package blume_system.config;

import org.bukkit.entity.Player;

public class CheckBanned {
	public static void check(Player p) {
		if (p.isBanned()) {
			DisconnectMessages.banned(p);
		}
	}
}
