package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.general.Main;
import blume_system.settings.Config;

public class AnimalOutOfZone {
	
	/*
	 * kill animals if out of zone
	 * 
	 * needs to be made more efficient but everyhthing works perfect
	 */
	
	public static int[] entityZones = AnimalZoneSort.zones;
	
	public AnimalOutOfZone() {
		for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) { //get all current entitys in the world
			if (e.getCustomName() != null && !e.isDead()) {
				if (e.getType() == Config.aTypeChicken && e.getCustomName().equals(Config.aNameChicken)) { //if entity is part of animal list
		    		if (checkZone1(e.getLocation()) == true) { //if entity not inside zone
		    			killAnimal(e);
		    		}
		    	}
				if (e.getType() == Config.aTypePig && e.getCustomName().equals(Config.aNamePig)) {
		    	  	
		    		if (checkZone2(e.getLocation()) == true) {
		    			killAnimal(e);
		    		}
		    	}
				if (e.getType() == Config.aTypeSheep && e.getCustomName().equals(Config.aNameSheep)) {
		    	  	
		    		if (checkZone3(e.getLocation()) == true) {
		    			killAnimal(e);
		    		}
		    	}
				if (e.getType() == Config.aTypeCow && e.getCustomName().equals(Config.aNameCow)) {
		    	  	
		    		if (checkZone4(e.getLocation()) == true) {
		    			killAnimal(e);
		    		}
		    	}
			}
    	}
	}
	
	public static void killAnimal(Entity e) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() { //needs a little delay
            @Override
            public void run() {
            	((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10000, 100)); //kill animal
            
            	Location ploc = new Location(e.getWorld(),
       	    			e.getLocation().getX(), //particle start position (0.5 mid of block)
       	    			e.getLocation().getY() + 0.1,
       	    			e.getLocation().getZ());
       	    	e.getWorld().spigot().playEffect(ploc
    						   	    			, Effect.CLOUD,
    						   	    			1, //id
    						   	    			1, //data
    						   	    			0.6f, //offsetX (particle spread)
    						   	    			0.6f, //offsetY
    						   	    			0.6f, //offsetZ
    						   	    			0, //speed
    						   	    			30, //particleCount (particles that spawn inside the zone)
    						   	    			30); //radius
            }
        }, 0);
	}
	
	private boolean checkZone1(Location entityLoc) { //if entity is inside defined zone
		boolean entityInsideZone = false;
		
		for (int i = 0; i < entityZones.length/6; i++) { //goes trough every zone (each zone for specific enemy type)
			
			//needs to be made more efficient
			if (entityLoc.getBlockX() < entityZones[0]) { //for x
				entityInsideZone = true;
			} else if (entityLoc.getBlockX() > entityZones[3]) {
				entityInsideZone = true;
			}
			
			if (entityLoc.getBlockZ() < entityZones[2]) { //for z
				entityInsideZone = true;
			} else if (entityLoc.getBlockZ() > entityZones[5]) {
				entityInsideZone = true;
			}
		}
		return entityInsideZone;
	}
	private boolean checkZone2(Location entityLoc) { //if entity is inside defined zone
		boolean entityInsideZone = false;

		for (int i = 0; i < entityZones.length/6; i++) { //goes trough every zone (each zone for specific enemy type)
			
			//needs to be made more efficient
			if (entityLoc.getBlockX() < entityZones[6]) { //for x
				entityInsideZone = true;
			} else if (entityLoc.getBlockX() > entityZones[9]) {
				entityInsideZone = true;
			}
			
			if (entityLoc.getBlockZ() < entityZones[8]) { //for z
				entityInsideZone = true;
			} else if (entityLoc.getBlockZ() > entityZones[11]) {
				entityInsideZone = true;
			}
		}
		return entityInsideZone;
	}
	private boolean checkZone3(Location entityLoc) { //if entity is inside defined zone
		boolean entityInsideZone = false;

		for (int i = 0; i < entityZones.length/6; i++) { //goes trough every zone (each zone for specific enemy type)
			
			//needs to be made more efficient
			if (entityLoc.getBlockX() < entityZones[12]) { //for x
				entityInsideZone = true;
			} else if (entityLoc.getBlockX() > entityZones[15]) {
				entityInsideZone = true;
			}
			
			if (entityLoc.getBlockZ() < entityZones[14]) { //for z
				entityInsideZone = true;
			} else if (entityLoc.getBlockZ() > entityZones[17]) {
				entityInsideZone = true;
			}
		}
		return entityInsideZone;
	}
	private boolean checkZone4(Location entityLoc) { //if entity is inside defined zone
		boolean entityInsideZone = false;
		
		for (int i = 0; i < entityZones.length/6; i++) { //goes trough every zone (each zone for specific enemy type)
			
			//needs to be made more efficient
			if (entityLoc.getBlockX() < entityZones[18]) { //for x
				entityInsideZone = true;
			} else if (entityLoc.getBlockX() > entityZones[21]) {
				entityInsideZone = true;
			}
			
			if (entityLoc.getBlockZ() < entityZones[20]) { //for z
				entityInsideZone = true;
			} else if (entityLoc.getBlockZ() > entityZones[23]) {
				entityInsideZone = true;
			}
		}
		return entityInsideZone;
	}
}
