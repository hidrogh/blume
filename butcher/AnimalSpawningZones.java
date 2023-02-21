package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.settings.Config;


public class AnimalSpawningZones implements Listener {
	
	/*
	 * animal spawning algorithm for butcher
	 */
	
	public static int[] zones = AnimalZoneSort.zones; //all zones (sorted)
	
	public AnimalSpawningZones() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() {
            @Override
            public void run() {
            	
            	if (AnimalContainer.getChickenAmount() < Config.aMaxAmountChicken) {
            		Location loc = randomZone(zones[0], zones[1], zones[2], zones[3], zones[4], zones[5]);
            		loc.getWorld().spawnEntity(loc, Config.aTypeChicken).setCustomName(Config.aNameChicken); //spawner
            	}
            	if (AnimalContainer.getPigAmount() < Config.aMaxAmountPig) {
            		Location loc = randomZone(zones[6], zones[7], zones[8], zones[9], zones[10], zones[11]);
            		loc.getWorld().spawnEntity(loc, Config.aTypePig).setCustomName(Config.aNamePig); 
            	}
            	if (AnimalContainer.getSheepAmount() < Config.aMaxAmountSheep) {
            		Location loc = randomZone(zones[12], zones[13], zones[14], zones[15], zones[16], zones[17]);
            		loc.getWorld().spawnEntity(loc, Config.aTypeSheep).setCustomName(Config.aNameSheep);
            	}
            	if (AnimalContainer.getCowAmount() < Config.aMaxAmountCow) {
            		Location loc = randomZone(zones[18], zones[19], zones[20], zones[21], zones[22], zones[23]);
            		loc.getWorld().spawnEntity(loc, Config.aTypeCow).setCustomName(Config.aNameCow);
            	}
            	
            }
        }, 1);
	    
	    int animalCount[] = {0,0,0,0};
	    for(Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
	    	if (e.getType() == Config.aTypeChicken && e.getCustomName() == Config.aNameChicken) {
	    		animalCount[0]++;
	    	}
	    	if (e.getType() == Config.aTypePig && e.getCustomName() == Config.aNamePig) {
	    		animalCount[1]++;
	    	}
	    	if (e.getType() == Config.aTypeSheep && e.getCustomName() == Config.aNameSheep) {
	    		animalCount[2]++;
	    	}
	    	if (e.getType() == Config.aTypeCow && e.getCustomName() == Config.aNameCow) {
	    		animalCount[3]++;
	    	}
	    }
	    AnimalContainer.setChickenAmount(animalCount[0]);
	    AnimalContainer.setPigAmount(animalCount[1]);
	    AnimalContainer.setSheepAmount(animalCount[2]);
	    AnimalContainer.setCowAmount(animalCount[3]);
	    
	    animalCount[0] = 0; //reset counters
	    animalCount[1] = 0;
	    animalCount[2] = 0;
	    animalCount[3] = 0;
	}
	
	public Location randomZone(int x, int y, int z, int x2, int y2, int z2) { //enter zone cords and get random inside back
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
}
