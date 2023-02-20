package blume_system.config;

import org.bukkit.entity.Player;

import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.settings.Config;

public class NewJoin {
	
	/*
	 * new player config
	 */
	
	public static void addPlayer(Player p) { //add player name to config file
		if (!Main.getPluginInstance().getConfig().contains("players.uuid-" + p.getUniqueId().toString())) { //if player joins the first time
			
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString(), ""); //add the player uuid to the uuid string
						
			Config.setNewPlayerData(p); //set columns for player data ("Config" class)
			
			Main.getPluginInstance().saveConfig(); //save config file
						
			System.out.print(Log.logInfo() + "player " + p.getUniqueId() + " is joined the first time and got added to the config file");
		} else {
			System.out.print(Log.logInfo() + "player " + p.getUniqueId() + " already exists inside the config file");
		}
	}
	
	public static void refreshUsername(Player p) { //refresh the player name if changed on the mojang/microsoft page
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".username", p.getName());
		Main.getPluginInstance().saveConfig();
	}
}
