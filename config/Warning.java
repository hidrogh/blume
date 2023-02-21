package blume_system.config;

import org.bukkit.entity.Player;

import blume_system.general.Main;

public class Warning {
	
	/*
	 * give warning points to players so operators can see how often a system error happend on one player
	 */
	
	public static void giveWarning(Player p) {
		int oldWarningAmount = Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".warned"));
		
		int newAmount = oldWarningAmount += 1;
		
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".warned", Integer.toString(newAmount));
	}
	
	public static int getWarningAmount(Player p) {
		return Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".warned"));
	}
}
