package blume_system.general;

import org.bukkit.scheduler.BukkitRunnable;

import blume_system.worldevent.Mine;

public class Updater {
	
	/*
	 * delay timer class
	 * 
	 * - updates will stop if no player is online
	 */
	
	int[] delayList = new int[100];
	boolean stopDelay = false;
	boolean oneTimeMessage = false;
	
	public Updater() {
		int delay = 20; //delay between updates (20 = 1sec, do not change)
		
		new BukkitRunnable() { //update process
			@Override
			public void run() {
				if (stopDelay == false) { //if no players are online the updater will stop
					
					delayA(); //every 1sec
					
					if (delayList[0] >= 3) { delayList[0] = 0; delayB(); } delayList[0]++; //every 3sec
					if (delayList[1] >= 5) { delayList[1] = 0; delayC(); } delayList[1]++; //every 5sec
					if (delayList[2] >= 10) { delayList[2] = 0; delayD(); } delayList[2]++; //every 10sec
					if (delayList[3] >= 30) { delayList[3] = 0; delayE(); } delayList[3]++; //every 30sec
					if (delayList[4] >= 60) { delayList[4] = 0; delayF(); } delayList[4]++; //every 1min
					if (delayList[5] >= 300) { delayList[5] = 0; delayG(); } delayList[5]++; //every 5min
					if (delayList[6] >= 600) { delayList[6] = 0; delayH(); } delayList[6]++; //every 10min
					if (delayList[7] >= 1800) { delayList[7] = 0; delayJ(); } delayList[7]++; //every 30min
				}
				
				/*
				 * - if no players are online anymore
				 * - the "Update" class will stop if no players are online
				 */
				if (Main.getPluginInstance().getServer().getOnlinePlayers().size() == 0) {
					if (oneTimeMessage == false) { //that this message gets send only one time
						System.out.print(Log.logInfo() + "no players are online; low-power mode now active; awaiting player(s)...");
						oneTimeMessage = true;
					}
					stopDelay = true;
				} else {
					oneTimeMessage = false;
					stopDelay = false;
				}
			}
		}.runTaskTimerAsynchronously(Main.getPluginInstance(), 0, delay);
	}
	
	/*
	 * CLASSES THAT GET UPDATED EVERY X TIME
	 */
	
	private void delayA() {
		//---[ every 1sec ]---
		
		new Mine(); //mine world generator
	}
	
	private void delayB() {
		//---[ every 3sec ]---
	}
	
	private void delayC() {
		//---[ every 5sec ]---
		
	}
	
	private void delayD() {
		//---[ every 10sec ]---
		
	}
	
	private void delayE() {
		//---[ every 30sec ]---
		
	}
	
	private void delayF() {
		//---[ every 1min ]---
		
	}
	
	private void delayG() {
		//---[ every 5min ]---
		
	}
	
	private void delayH() {
		//---[ every 10min ]---
		
	}
	
	private void delayJ() {
		//---[ every 30min ]---
		
	}
}
