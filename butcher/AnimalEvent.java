package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.general.Main;
import blume_system.settings.Config;

public class AnimalEvent implements Listener {
	@EventHandler
	public void onZombieSpawn(CreatureSpawnEvent e){
		
		//needs a little delay because this event cant detect the name right away
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() {
            @Override
            public void run() {
            	
                if (e.getEntity() instanceof LivingEntity && e.getEntity().getCustomName() != null) {
                	if (e.getEntity().getCustomName().equals(Config.aNameChicken) && e.getEntity().getType() == Config.aTypeChicken || 
                		e.getEntity().getCustomName().equals(Config.aNamePig) && e.getEntity().getType() == Config.aTypePig ||
                		e.getEntity().getCustomName().equals(Config.aNameSheep) && e.getEntity().getType() == Config.aTypeSheep ||
                		e.getEntity().getCustomName().equals(Config.aNameCow) && e.getEntity().getType() == Config.aTypeCow) {
                		
                		((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10000, 255)); //no walk
                	}
                }
                
            }
        }, 1);
    }
}
