package blume_system.chat.usercommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blume_system.chat.ChatTags;
import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class CommandRanks implements CommandExecutor {
	
	/*
	 * show all ranks and how much points are needed, rtank system in config needs to be made better
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			int playerPoints = Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"));
			
	        p.sendMessage("");
	        p.sendMessage(ChatTags.logBlume() + ChatColor.GRAY + "List of all ranks: (Your points: " + ChatColor.GRAY + playerPoints + ")");
	        if (playerPoints < Config.r2_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r1c + Config.rank1.substring(0, 1).toUpperCase() + Config.rank1.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + "0" + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r1c + Config.rank1.substring(0, 1).toUpperCase() + Config.rank1.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + "0");
	        }
	        	
	        if (playerPoints >= Config.r2_points && playerPoints < Config.r3_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r2c + Config.rank2.substring(0, 1).toUpperCase() + Config.rank2.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r2_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r2c + Config.rank2.substring(0, 1).toUpperCase() + Config.rank2.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r2_points);
	        }
	        
	        if (playerPoints >= Config.r3_points && playerPoints < Config.r4_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r3c + Config.rank3.substring(0, 1).toUpperCase() + Config.rank3.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r3_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r3c + Config.rank3.substring(0, 1).toUpperCase() + Config.rank3.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r3_points);
	        }
	        	
	        if (playerPoints >= Config.r4_points && playerPoints < Config.r5_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r4c + Config.rank4.substring(0, 1).toUpperCase() + Config.rank4.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r4_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r4c + Config.rank4.substring(0, 1).toUpperCase() + Config.rank4.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r4_points);
	        }
	        	
	        if (playerPoints >= Config.r5_points && playerPoints < Config.r6_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r5c + Config.rank5.substring(0, 1).toUpperCase() + Config.rank5.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r5_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r5c + Config.rank5.substring(0, 1).toUpperCase() + Config.rank5.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r5_points);
	        }
	        	
	        if (playerPoints >= Config.r6_points && playerPoints < Config.r7_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r6c + Config.rank6.substring(0, 1).toUpperCase() + Config.rank6.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r6_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r6c + Config.rank6.substring(0, 1).toUpperCase() + Config.rank6.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r6_points);
	        }
	        	
	        if (playerPoints >= Config.r7_points) {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r7c + Config.rank7.substring(0, 1).toUpperCase() + Config.rank7.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r7_points + ChatColor.GRAY + " (Your rank)");
	        } else {
	        	p.sendMessage(ChatColor.GRAY + "     - " + Config.r7c + Config.rank7.substring(0, 1).toUpperCase() + Config.rank7.substring(1) + ChatColor.GRAY + ": " + ChatColor.WHITE + Config.r7_points);
	        }
	        
	        p.sendMessage("");
	    }
		
	    return true;
	 }
}
