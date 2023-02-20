package blume_system.mining;

import org.bukkit.Location;

import blume_system.settings.Config;

public class MiningZoneSort {
	
	/*
	 * mining sort algorithm
	 */
	public static Location[] getMiningZonesSorted() {
		//importing all the zones from the "Config" class
		int readCounter = 0;
		int[] miningZones = Config.getMiningZones(); //get mining zones from "Config" class
		Location[] locationWhitelist = new Location[miningZones.length/3];
				
		for (int i2 = 0; i2 < miningZones.length/3; i2++) { //calculates the needed amout of whitelist array values
			locationWhitelist[i2] = new Location(Mining.getBlockWorld(), miningZones[readCounter], miningZones[readCounter+1], miningZones[readCounter+2]);
			readCounter += 3;
		}
				
		//this algorithm will sort the values so that the smaller values will be on the first variable
		int lwSortCounter = 0;
		int newValue = 0; //temporary save for new values
		for (int i = 0; i < locationWhitelist.length/2; i++) {
			//for x
			if (locationWhitelist[lwSortCounter].getBlockX() > locationWhitelist[lwSortCounter+1].getBlockX()) {
				//swap values
				newValue = locationWhitelist[lwSortCounter].getBlockX();
						
				locationWhitelist[lwSortCounter].setX(locationWhitelist[lwSortCounter+1].getBlockX());
				locationWhitelist[lwSortCounter+1].setX(newValue);
			}
			//for y
			if (locationWhitelist[lwSortCounter].getBlockY() > locationWhitelist[lwSortCounter+1].getBlockY()) {
				//swap values
				newValue = locationWhitelist[lwSortCounter].getBlockY();
						
				locationWhitelist[lwSortCounter].setY(locationWhitelist[lwSortCounter+1].getBlockY());
				locationWhitelist[lwSortCounter+1].setY(newValue);
			}
			//for z
			if (locationWhitelist[lwSortCounter].getBlockZ() > locationWhitelist[lwSortCounter+1].getBlockZ()) {
				//swap values
				newValue = locationWhitelist[lwSortCounter].getBlockZ();
						
				locationWhitelist[lwSortCounter].setZ(locationWhitelist[lwSortCounter+1].getBlockZ());
				locationWhitelist[lwSortCounter+1].setZ(newValue);
			}
			lwSortCounter += 2;
		}
				
		return locationWhitelist;
	}
}
