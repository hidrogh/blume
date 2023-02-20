package blume_system.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import blume_system.general.Main;

public class PlayerWrite implements Listener {
	
	/*
	 * when players write into the chat
	 */
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) { //still works
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		String rank = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".status");
		
		if (p.isOp()) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Operator" + ChatColor.GRAY + "] " + p.getName() + ": " + e.getMessage());
		} else {
			p.sendMessage(ChatColor.GRAY + "[" + rank.substring(0, 1).toUpperCase() + rank.substring(1) + "] " + p.getName() + ": " + e.getMessage());
		}
	}
}
