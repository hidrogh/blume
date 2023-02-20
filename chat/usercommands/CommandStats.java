package blume_system.chat.usercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blume_system.chat.ChatTags;
import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class CommandStats implements CommandExecutor {
	
	/*
	 * stats command
	 */
	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { //if player enters a command
    	if (sender instanceof Player) {
            Player p = (Player) sender;

            p.sendMessage("");
            p.sendMessage(ChatTags.logBlume() + ChatColor.GRAY + p.getName() + "'s stats: (Rank: " + Config.getGlobalRank(p) + ChatColor.GRAY + ")");
            p.sendMessage(ChatColor.GRAY + "     Money" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".currency") + "$");
            p.sendMessage(ChatColor.GRAY + "     Level [Global]" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"));
            p.sendMessage(ChatColor.GRAY + "     Level [Miner]" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_miner"));
            p.sendMessage(ChatColor.GRAY + "     Level [Farmer]" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_farmer"));
            p.sendMessage(ChatColor.GRAY + "     Level [Butcher]" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_butcher"));
            p.sendMessage(ChatColor.GRAY + "     Level [Warrior]" + ": " + ChatColor.WHITE + Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_warrior"));
            p.sendMessage("");
    	}

        
        return true; // If the player (or console) uses our command correct, we can return true
    }
}
