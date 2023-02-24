package blume_system.chat.operatorcommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.chat.ChatMessages;
import blume_system.chat.ChatTags;
import blume_system.chat.VillagerController;
import blume_system.config.ConfigSettings;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class CommandSpawn_level implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player p = (Player) sender;
            String name = Config.villager[3];
            int counter = 0;
            
            if (p.isOp()) {
            	for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) { //kill old buyer
            		if (e.getCustomName() != null) {
            			if (e.getCustomName().equals(name)) {
                			((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, 100));
                			p.sendMessage(ChatTags.logSystem() + "Old seller villager got killed.");
                		}
            		}
            	}
            	
                Villager villager = (Villager)Bukkit.getWorld(Config.getWorldName()).spawnEntity(p.getLocation(), EntityType.VILLAGER);
                villager.setCustomName(name);
                ((CraftVillager)villager).getHandle().setProfession(2); //type
                
                //Location loc = p.getLocation();
            	//loc.getWorld().spawnEntity(loc, EntityType.VILLAGER).setCustomName(name);
            	
            	for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) { //kill old buyer
            		if (e.getCustomName() != null) {
            			if (e.getCustomName().equals(name)) {
            				VillagerController.villagerPotions(e);
            				ConfigSettings.setVillagerLevel(p.getLocation());
            			}
            		}
            	}
            
            } else {
            	ChatMessages.notFound(p);
            }
    	}
        
        return true;
	}
}
