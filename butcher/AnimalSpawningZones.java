package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.settings.Config;


public class AnimalSpawningZones implements Listener {
	
	/*
	 * animal spawning algorithm for butcher
	 */
	
	public static int[] zones = AnimalZoneSort.zones; //all zones (sorted)
	public static Location loc;
	
	public static void animalAmountRefresh() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() {
            @Override
            public void run() {
            	/*
            	 * it needs to be = not > or <
            	 */
            	
            	//set current amount of animals in the world
            	setAnimalCounts();
            	
            	//chicken controller
            	if (Bukkit.getWorld(Config.getWorldName()).getPlayers().size() != 0) {
            		
	            	if (getAnimalCount(Config.aTypeChicken) < Config.aMaxAmountChicken) {
	            		loc = randomZone(zones[0], zones[1], zones[2], zones[3], zones[4], zones[5]);
	            		loc.getWorld().spawnEntity(loc, Config.aTypeChicken).setCustomName(Config.aNameChicken); //spawner
	            	}
	            	if (getAnimalCount(Config.aTypeChicken) > Config.aMaxAmountChicken) {
	            		killAnimal(Config.aTypeChicken);
	            	}
	            	
	            	//pig controller
	            	if (getAnimalCount(Config.aTypePig) < Config.aMaxAmountPig) {
	            		loc = randomZone(zones[6], zones[7], zones[8], zones[9], zones[10], zones[11]);
	            		loc.getWorld().spawnEntity(loc, Config.aTypePig).setCustomName(Config.aNamePig); 
	            	}
	            	if (getAnimalCount(Config.aTypePig) > Config.aMaxAmountPig) {
	            		killAnimal(Config.aTypePig);
	            	}
	            	
	            	//sheep controller
	            	if (getAnimalCount(Config.aTypeSheep) < Config.aMaxAmountSheep) {
	            		loc = randomZone(zones[12], zones[13], zones[14], zones[15], zones[16], zones[17]);
	            		loc.getWorld().spawnEntity(loc, Config.aTypeSheep).setCustomName(Config.aNameSheep);
	            	}
	            	if (getAnimalCount(Config.aTypeSheep) > Config.aMaxAmountSheep) {
	            		killAnimal(Config.aTypeSheep);
	            	}
	            	
	            	//cow controller
	            	if (getAnimalCount(Config.aTypeCow) < Config.aMaxAmountCow) {
	            		loc = randomZone(zones[18], zones[19], zones[20], zones[21], zones[22], zones[23]);
	            		loc.getWorld().spawnEntity(loc, Config.aTypeCow).setCustomName(Config.aNameCow);
	            	}
	            	if (getAnimalCount(Config.aTypeCow) > Config.aMaxAmountCow) {
	            		killAnimal(Config.aTypeCow);
	            	}
            	}
            }
        }, 1);
	}
	
	public static Location randomZone(int x, int y, int z, int x2, int y2, int z2) { //enter zone cords and get random inside back
		int X = (int)(Math.random()*(x2-x))+x;
		int Y = 0; //can not be random because it is y
		int Z = (int)(Math.random()*(z2-z))+z;
		
		Location loc = new Location(Bukkit.getWorld(Config.getWorldName()), X, Y, Z); //this will get instantly overwritten
		
		//rZ needs to be on the ground
		for (int i = 0; i < Bukkit.getWorld(Config.getWorldName()).getMaxHeight(); i++) {
			if (Config.animalMove == true) { //if the animals will spawn in the mid of 4 blocks and not perfectly on one block this will go true ("Config" class)
				loc = new Location(Bukkit.getWorld(Config.getWorldName()), X+0.5, Y, Z+0.5); //new random location	
			} else {
				loc = new Location(Bukkit.getWorld(Config.getWorldName()), X, Y, Z); //new random location	
			}
			
			if (loc.getBlock().getType() == Material.AIR) {
				break; //if perfect height found
			} else {
				Y++; //if height "still" wrong
			}
			
			if (i >= Bukkit.getWorld(Config.getWorldName()).getMaxHeight() - 1) { //if no block found on y (all blocks from 0 to max level are used)
				System.out.print(Log.logError() + "animal spawn zone not ideal, please fix the zone");
				loc = new Location(Bukkit.getWorld(Config.getWorldName()), X, -100, Z);
			}
		}

		return loc;
	}
	
	public static void killAnimal(EntityType animalType) {
		if (Bukkit.getWorld(Config.getWorldName()).getPlayers().size() != 0) { //to prevent bugs if server needs to load entity numbers first
			int counter = 0;
			int counter2 = 0;
			for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) { //count all specific animals
	    		if (e.getType() == animalType) {
	    			counter++;
	    		}
	    	}
			for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) { //only count this specific animal
	    		if (e.getType() == animalType && counter2 < counter) {
	    			counter2++;
	    			
	    			((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10000, 100)); //kills instantly
	    		}
	    	}
		}
	}
	
	public static int getAnimalCount(EntityType animalType) {
		int counter = 0;
		for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
    		if (e.getCustomName() != null) {
    			if (e.getType() == animalType) {
    	    		counter++;
    	    	}
    		}
    	}
		return counter;
	}
	
	public static void setAnimalCounts() {
    	AnimalController.setChickenAmount(getAnimalCount(Config.aTypeChicken));
    	AnimalController.setPigAmount(getAnimalCount(Config.aTypePig));
    	AnimalController.setSheepAmount(getAnimalCount(Config.aTypeSheep));
    	AnimalController.setCowAmount(getAnimalCount(Config.aTypeCow));
	}
}
