package blume_system.general;

public class Switches {
	
	/*
	 * switches for protection classes
	 */
	
	//world protection - block break
	static boolean stopStopWPBlockBreak;
	public static void setStopWPBlockBreak(boolean b) {
		stopStopWPBlockBreak = b;
	}
	public static boolean getStopWPBlockBreak() {
		return stopStopWPBlockBreak;
	}
	
	//world protection - block place
	static boolean stopStopWPBlockPlace;
	public static void setStopWPBlockPlace(boolean b) {
		stopStopWPBlockPlace = b;
	}
	public static boolean getStopWPBlockPlace() {
		return stopStopWPBlockPlace;
	}
	
	//world protection - item spawn
	static boolean stopStopWPItemSpawn;
	public static void setStopWPItemSpawn(boolean b) {
		stopStopWPItemSpawn = b;
	}
	public static boolean getStopWPItemSpawn() {
		return stopStopWPItemSpawn;
	}
	
	//world protection - block xp drop
	static boolean stopStopWPBlockExp;
	public static void setStopWPBlockExp(boolean b) {
		stopStopWPBlockExp = b;
	}
	public static boolean getStopWPBlockExp() {
		return stopStopWPBlockExp;
	}
	
	//world protection - player item drop
	static boolean stopStopWPPlayerDropItem;
	public static void setStopWPPlayerDropItem(boolean b) {
		stopStopWPPlayerDropItem = b;
	}
	public static boolean getStopWPPlayerDropItem() {
		return stopStopWPPlayerDropItem;
	}
}
