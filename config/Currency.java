package blume_system.config;

import org.bukkit.entity.Player;
import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.settings.Config;

public class Currency {
	
	/*
	 * currency/economy methods and algorithms
	 */
	
	public static void addCurrency(int amount, Player p) { //amount and playername
		String oldCurrency = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".currency");
		
		int sum = Integer.parseInt(oldCurrency) + amount;
		
		if (sum > Config.maxMoneyAmount) { //if amount of money goes over max amount inside the config
			sum = 0;
			
			Warning.giveWarning(p);
			
			DisconnectMessages.kickForError(p);
		}
		if (sum < 0) { //negative amount of money trough a bug
			System.out.print(Log.logError() + p.getName() + " [" + p.getUniqueId() + "] got a negative amount of money");
			sum = 0;
			
			DisconnectMessages.kickForError(p);
		}
		
		Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".currency", Integer.toString(sum));
		
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
