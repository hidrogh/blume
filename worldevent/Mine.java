package blume_system.worldevent;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.mining.Mining;
import blume_system.mining.MiningZoneSort;
import blume_system.settings.Config;

public class Mine {
	
	/*
	 * world event mine ore pattern generator
	 * 
	 * - random blocks inside the zone will get randomly replaced every x time
	 * - the chance is different for each block
	 * - only ores or stone blocks will get randomly replaced by a random block
	 */
	
	public Mine() {
		if (Config.userZones == true) {
			for (int i = 0; i < 50; i++) {
				if (Bukkit.getServer().getWorld("world").getPlayers().size() > 0) { //if map is not loadet, .getWorld will output a error
					//get random location inside the zone
					int rn1 = (int) (Math.random()*(MiningZoneSort.getMiningZonesSorted()[1].getBlockX()-MiningZoneSort.getMiningZonesSorted()[0].getBlockX())) + MiningZoneSort.getMiningZonesSorted()[0].getBlockX();
					int rn2 = (int) (Math.random()*(MiningZoneSort.getMiningZonesSorted()[1].getBlockY()-MiningZoneSort.getMiningZonesSorted()[0].getBlockY())) + MiningZoneSort.getMiningZonesSorted()[0].getBlockY();
					int rn3 = (int) (Math.random()*(MiningZoneSort.getMiningZonesSorted()[1].getBlockZ()-MiningZoneSort.getMiningZonesSorted()[0].getBlockZ())) + MiningZoneSort.getMiningZonesSorted()[0].getBlockZ();
					Location rLocation = new Location(Bukkit.getServer().getWorld(Config.getWorldName()), rn1, rn2, rn3);
					
					if (ArrayUtils.contains(Mining.blockWhitelist, rLocation.getBlock().getType()) ||
						rLocation.getBlock().getType() == Mining.blockReplaceMaterial ||
						rLocation.getBlock().getType() == Material.STONE) { //if random block is stone, cobblestone or ore
						
						Material replacementBlock = getReplacementBlock();
						//System.out.print(Log.logEvent() + "worldevent -> mine [block: " + rLocation.getBlock().getType() + " at: x(" + rLocation.getBlockX() + ") y(" + rLocation.getBlockY() + ") z(" + rLocation.getBlockZ() + ") to: " + replacementBlock + "]");
			    		
			    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() {
			                @Override
			                public void run() {
			                	Bukkit.getServer().getWorld(Config.getWorldName()).getBlockAt(rLocation).setType(replacementBlock); //place block
			                }
			            }, 0);
			    		
			    		break;
					}
					
					if (i >= 49) { //if no block found after 50 tries (nothing bad will happen if this accures, it will just wait for another update)
						System.err.print(Log.logError() + "no ideal block found inside mining zone after 50 tries; please optimize the mining area");
					}
				} else {
					System.out.print(Log.logError() + "no player on the server, mine block-check now stopped to prevent \"getWorld\" warning");
					break;
				}
			}
		}
	}
	
	public Material getReplacementBlock() {
		int[] blockProb = Config.getMiningBlockProb(); //get block chances
		double[] values = new double[10];
		int[] winner = new int[10];
		Material winningBlock = null;
		
		//norming not needed
		//		//norming all "blockProb" values
		//		for (int i = 0; i < blockProb.length; i++) {
		//			values[i] = ((blockProb[i]-(double)Arrays.stream(blockProb).min().getAsInt())/((double)Arrays.stream(blockProb).max().getAsInt()-(double)Arrays.stream(blockProb).min().getAsInt()));
		//		}
		
		//calculate chances evenly
		for (int i2 = 0; i2 < 50; i2++) {
			for (int i = 0; i < blockProb.length; i++) {
				values[i] = (double)blockProb[i]/(double)Arrays.stream(blockProb).sum();
				
				if (Math.random() < values[i]) {
					winner[i] = 1;
				}
			}
			if (Arrays.stream(winner).sum() < 2 && Arrays.stream(winner).sum() > 0) {
				break;
			} else {
				for (int i = 0; i < winner.length; i++) {
					winner[i] = 0;
				}
			}
		}
		if (Arrays.stream(winner).sum() > 2 || Arrays.stream(winner).sum() < 0) {
			winningBlock = Material.STONE;
		}
		
		if (winner[0] == 1) {
			winningBlock = Material.STONE;
		} else if (winner[1] == 1) {
			winningBlock = Material.COAL_ORE;
		} else if (winner[2] == 1) {
			winningBlock = Material.IRON_ORE;
		} else if (winner[3] == 1) {
			winningBlock = Material.GOLD_ORE;
		} else if (winner[4] == 1) {
			winningBlock = Material.DIAMOND_ORE;
		} else if (winner[5] == 1) {
			winningBlock = Material.EMERALD_ORE;
		}
		
		return winningBlock;
	}
}
