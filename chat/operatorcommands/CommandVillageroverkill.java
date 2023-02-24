package blume_system.chat.operatorcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.chat.ChatMessages;
import blume_system.chat.ChatTags;
import blume_system.chat.VillagerController;
import blume_system.config.ConfigSettings;
import blume_system.config.Level;
import blume_system.config.PlayerScoreboard;
import blume_system.general.Main;
import blume_system.settings.Config;

public class CommandVillageroverkill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player p = (Player) sender;
            
            if (p.isOp()) {
            	for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
            		if (e.getCustomName() != null) {
            			if (e.getCustomName().equals(Config.villager[0]) ||
            				e.getCustomName().equals(Config.villager[1]) ||
            				e.getCustomName().equals(Config.villager[2]) ||
            				e.getCustomName().equals(Config.villager[3])) {
            				
            				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, 100));
            			}
            		}
            	}
            	
            	p.sendMessage(ChatTags.logSystem() + "All vilagers got killed.");
            } else {
            	ChatMessages.notFound(p);
            }
    	}
        
        return true;
	}
}
