
package blume_system.config;

import org.bukkit.entity.Player;

import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.settings.Config;

public class Level {
	
	/*
	 * level system for miner, farmer...
	 */
	
	public static void addLevelMiner(int amount, Player p) { //amount and playername
		String oldLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_miner");
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_miner", Integer.parseInt(oldLevel) + amount);
		
		System.out.print(Log.logEvent() + "miner_level added" + "[player: " + p.getName() + " got " + oldLevel + " and now " + (Integer.parseInt(oldLevel) + amount));
	
		globalLevel(p); //set global level
		Main.getPluginInstance().saveConfig(); //save config file
	}
	
	
	
	public static void globalLevel(Player p) { //add all levels together
		String minerLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_miner");
		String farmerLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_farmer");
		String butcherLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_butcher");
		String warriorLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_warrior");
		
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_global", Integer.parseInt(minerLevel) + Integer.parseInt(farmerLevel) + Integer.parseInt(butcherLevel) + Integer.parseInt(warriorLevel));
		
		Config.refreshGlobalRank(p); //refresh rank inside config file
		
		new Rankup(p); //rankup messages if possible (global rank)
	}
}
