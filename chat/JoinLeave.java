package blume_system.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import blume_system.config.Currency;
import blume_system.config.NewJoin;
import blume_system.config.PlayerScoreboard;
import blume_system.config.RankupContainer;
import blume_system.config.Tablist;
import blume_system.general.Main;
import net.md_5.bungee.api.ChatColor;

public class JoinLeave implements Listener {
	
	/*
	 * join and leave event
	 */
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		//join message
		if (e.getPlayer().isOp()) {
			e.setJoinMessage(ChatColor.RED + e.getPlayer().getName() + ChatColor.GRAY + " joined");
		} else {
			e.setJoinMessage(ChatColor.GRAY + e.getPlayer().getName() + " joined");
		}
        
        NewJoin.addPlayer(e.getPlayer()); //add playername to config file if not contained
        NewJoin.refreshUsername(e.getPlayer()); //refresh username if changed at mojang/microsoft
        
        Currency.syncXP(e.getPlayer()); //sync currency from the config file with the ingame xp
        
		String currentStatus = Main.getPluginInstance().getConfig().getString("players.uuid-" + e.getPlayer().getUniqueId().toString() + ".status");
		RankupContainer.setCurrentStatus(currentStatus);
		
		Tablist.setTablist(e.getPlayer()); //set tablist
		
		PlayerScoreboard.create(e.getPlayer()); //create scoreboard
	}
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
		//quit message
		if (e.getPlayer().isOp()) {
			e.setQuitMessage(ChatColor.RED + e.getPlayer().getName() + ChatColor.GRAY + " joined");
		} else {
			e.setQuitMessage(ChatColor.GRAY + e.getPlayer().getName() + " left");
		}
    }
	
	public void operatorMessage(Player p) { //not in use
		p.sendMessage(ChatTags.logSystem() + "You joined as an operator. Do not forget to rejoin if you change your permission level or reload the server.");
	}
}
