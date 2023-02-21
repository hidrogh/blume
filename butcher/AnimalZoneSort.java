package blume_system.butcher;

import blume_system.general.Log;
import blume_system.settings.Config;

public class AnimalZoneSort {
	
	/*
	 * sorting of animal spawning zones
	 * 
	 * - this class will run at the start of the plugin
	 * - after this class other classes can use the array "zones" with the sorted values
	 */
	
	public static int[] zones = Config.getAnimalSpawningZones();
	
	public AnimalZoneSort() {
		int a = 0;
		for (int i = 0; i < zones.length/6; i++) { //get zone from and to (rows)
			int tempSave = 0;
			
			if (zones[a] > zones[a+3]) { //if zone0 (from) is bigger than zone3 (to)
				tempSave = zones[a];
				
				zones[a] = zones[a+3];
				zones[a+3] = tempSave;
			}
			if (zones[a+1] > zones[a+4]) {
				tempSave = zones[a+1];
				
				zones[a+1] = zones[a+4];
				zones[a+4] = tempSave;
			}
			if (zones[a+2] > zones[a+5]) {
				tempSave = zones[a+2];
				
				zones[a+2] = zones[a+5];
				zones[a+5] = tempSave;
			}
			
			a += 6; //change row start int
		}
		System.out.print(Log.logInfo() + "animal zones got sorted (total: " + zones.length/6 + ")");
	}
}
