package blume_system.butcher;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import blume_system.settings.Config;

public class AnimalSpawningZones {
	
	/*
	 * animal spawning algorithm for butcher
	 */
	
	public AnimalSpawningZones() {
		int[] zones = AnimalZoneSort.zones; //all zones (sorted)
		
		World world = Bukkit.getWorld(Config.getWorldName());
	    Location loc = new Location(world, 10, 50, 9);
	    
	    //loc.getWorld().spawnEntity(loc, EntityType.COW);
	}
}
