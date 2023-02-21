package blume_system.mining;

import org.bukkit.Location;

import blume_system.settings.Config;

public class MiningContainer {
	static Location[] miningZonesSorted = new Location[Config.getMiningZones().length/3];
	
	public static void setMiningZones(Location[] zones) {
		miningZonesSorted = zones;
	}
	public static Location[] getMiningZonesSorted() {
		return miningZonesSorted;
	}
}
