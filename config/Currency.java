package blume_system.config;

import org.bukkit.entity.Player;
import blume_system.general.Log;
import blume_system.general.Main;

public class Currency {
	
	/*
	 * currency/economy methods and algorithms
	 */
	
	public static void addCurrency(int amount, Player p) { //amount and playername
		String oldCurrency = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".currency");
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".currency", Integer.parseInt(oldCurrency) + amount);
		
		System.out.print(Log.logEvent() + "currency added" + "[player: " + p.getName() + " got " + oldCurrency + " and now " + (Integer.parseInt(oldCurrency) + amount));
	
		Main.getPluginInstance().saveConfig(); //save config file
		
		PlayerScoreboard.refresh(p); //refresh scoreboard
	}
	
	public static void syncXP(Player p) {
		String currencyAmount = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".currency");
		
		p.setExp(0);
		//p.setLevel(Integer.parseInt(currencyAmount)); //not in use
		p.setLevel(0);
	}
}
