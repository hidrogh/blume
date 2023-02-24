package blume_system.chat.operatorcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blume_system.chat.ChatMessages;
import blume_system.chat.ChatTags;
import blume_system.config.Level;
import blume_system.config.PlayerScoreboard;
import blume_system.general.Main;

public class CommandMakemepro implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player p = (Player) sender;
            
            if (p.isOp()) {
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_global", "100000");
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_miner", "100000");
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_farmer", "100000");
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_butcher", "100000");
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".level_warrior", "100000");
            	Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".currency", "1000000");
            	
            	Level.globalLevel(p); //refresh stats
            	
            	p.sendMessage(ChatTags.logSystem() + "All stats now maxed.");
            } else {
            	ChatMessages.notFound(p);
            }
    	}
        return true;
	}
}
