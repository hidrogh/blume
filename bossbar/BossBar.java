package blume_system.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class BossBar {
	
	/*
	 * enderdragon control amount and position
	 * 
	 * enderdragon needs to be spawned at all directions
	 * 
	 * ENDERDRAGON WILL DISAPEAR AFTER 64 BLOCKS
	 */
	
//	public static String message = ChatColor.RED + "TEST";
//	
//	public BossBar() {
//		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() { //has to run a litte bit late because the join event will overwrite
//            @Override
//            public void run() {
//               	int counter = 0;
//            	
//            	for (Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
//            		if (EntityType.ENDER_DRAGON == e.getType()) {
//            			counter++;
//            		}
//            	}
//            	
//            	if (counter > 1) { //more than one dragon
//            		for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
//            			if (EntityType.ENDER_DRAGON == e.getType()) {
//            				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10000, 100));
//            			}
//            		}
//            	} else if (counter == 1) { //one dragon exists
//                	for (Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
//                		if (EntityType.ENDER_DRAGON == e.getType()) {
//                			e.teleport(BossBarController.getBossBarLoc());
//                			e.setCustomName(message);
//                		}
//                	}
//            	} else if (counter < 1) { //less than one dragon
//            		BossBarController.getBossBarLoc().getWorld().spawnEntity(BossBarController.getBossBarLoc(), EntityType.ENDER_DRAGON);
//            	}
//            }
//        }, 1);
//	}
//	
//	public static void setBossBarMessage(String s) {
//		message = s;
//	}
}
