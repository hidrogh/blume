
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
		
		globalLevel(p); //set global level
		Main.getPluginInstance().saveConfig(); //save config file
	}
	
	public static void addLevelButcher(int amount, Player p) {
		String oldLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_butcher");
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_butcher", Integer.parseInt(oldLevel) + amount);

		globalLevel(p);
		Main.getPluginInstance().saveConfig();
	}
	
	public static void globalLevel(Player p) { //add all levels together
		String minerLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_miner");
		String farmerLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_farmer");
		String butcherLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_butcher");
		String warriorLevel = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_warrior");
		
		int sum = Integer.parseInt(minerLevel) + Integer.parseInt(farmerLevel) + Integer.parseInt(butcherLevel) + Integer.parseInt(warriorLevel);
		
		if (sum < 0) { //negative amount of level (not possible without a bug)
			System.out.print(Log.logError() + p.getName() + " [" + p.getUniqueId() + "] got a negative amount of level");
			sum = 0;
			
			DisconnectMessages.kickForError(p);
		}
		if (sum >= Config.maxLevelAmount) { //if player goes higher than java integer value (max amount of money) every values will get reset to prevent errors
			sum = 0;
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", Config.rank1);
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_global", "0");
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_miner", "0");
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_farmer", "0");
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_butcher", "0");
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_warrior", "0");
			
			Warning.giveWarning(p);
			
			DisconnectMessages.kickForError(p);
		}
		
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_global", Integer.toString(sum));
		
		Config.refreshGlobalRank(p); //refresh rank inside config file
		
		Tablist.setTablist(p); //refresh tablist
		PlayerScoreboard.refresh(p); //refresh scoreboard
		
		if (!p.isOp()) { //op will never rank up (to prevent errors)
			new Rankup(p); //rankup messages if possible (global rank)
		}
	}
}
