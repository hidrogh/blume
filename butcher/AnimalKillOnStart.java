package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.settings.Config;

public class AnimalKillOnStart {
	
	/*
	 * killing all "old" animals after start, reset or reload
	 */
	
	public static void killAnimals() {
    	for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
    		if (e.getType() == Config.aTypeChicken ||
    			e.getType() == Config.aTypePig ||
    			e.getType() == Config.aTypeSheep ||
    			e.getType() == Config.aTypeCow) {
    			
    			((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM,10000, 100)); //kills instantly
    		}
    	}
	}
}
