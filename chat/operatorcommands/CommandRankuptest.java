package blume_system.chat.operatorcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blume_system.chat.ChatMessages;
import blume_system.chat.ChatTags;
import blume_system.config.Rankup;

public class CommandRankuptest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player p = (Player) sender;
            
            if (p.isOp()) {
            	Rankup.rankupSequenz(p);
                
                p.sendMessage(ChatTags.logSystem() + "Rankup sequenz got played.");
            } else {
            	ChatMessages.notFound(p);
            }
    	}
        
        return true; // If the player (or console) uses our command correct, we can return true
	}

}
