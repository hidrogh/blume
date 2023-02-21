package blume_system.mining;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import blume_system.general.Log;
import blume_system.general.Main;
import blume_system.general.Switches;
import blume_system.settings.Config;

public class Mining implements Listener {
	
	/*
	 * player mining system
	 */
	
	public static Material blockReplaceMaterial = Material.COBBLESTONE; //block replacement material
	public static Material[] blockWhitelist = { Material.COAL_ORE,
										  		Material.IRON_ORE,
										  		Material.GOLD_ORE,
										  		Material.DIAMOND_ORE,
										  		Material.EMERALD_ORE }; //block that the player is allowed to break
	
	public static World blockWorld;
	public static Player player;
	public static Material blockMaterial;
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Location blockLoc = e.getBlock().getLocation(); //get block location
		Material blockType = e.getBlock().getType(); //block material
		World blockWorld = e.getBlock().getWorld(); //block world
		
		setPlayer(e.getPlayer());
		setBlockWorld(blockWorld);
		setBlockMaterial(blockType);
		
		//get values from "MiningZoneSort.getMiningZonesSorted()"
		int lw_counter = 0;
		
    	for (int i2 = 0; i2 < MiningContainer.getMiningZonesSorted().length/2; i2++) { //go trough all available zones
    		if (blockLoc.getBlockX() >= MiningContainer.getMiningZonesSorted()[lw_counter].getBlockX() && blockLoc.getBlockX() <= MiningContainer.getMiningZonesSorted()[lw_counter+1].getBlockX() &&
    			blockLoc.getBlockY() >= MiningContainer.getMiningZonesSorted()[lw_counter].getBlockY() && blockLoc.getBlockY() <= MiningContainer.getMiningZonesSorted()[lw_counter+1].getBlockY() &&
    			blockLoc.getBlockZ() >= MiningContainer.getMiningZonesSorted()[lw_counter].getBlockZ() && blockLoc.getBlockZ() <= MiningContainer.getMiningZonesSorted()[lw_counter+1].getBlockZ() &&
    			!e.getPlayer().isOp()) { //checking if block location is between "from" and "to"
    			
    			
    			for (int i = 0; i < blockWhitelist.length; i++) { //did the player broke a allowed block?
    				if (blockType.equals(blockWhitelist[i])) {
    					//System.out.print(Log.logEvent() + "mining -> mining [player:" + e.getPlayer().getName() + " farmed block:" + e.getBlock().getType() + "]");
    					
	            		Switches.setStopWPBlockBreak(true); //stop the "WorldProtection" class in this case
	            		
	            		
	            		/* 
	            		 * - delay after block break
	            		 * - block wont get replaced if no delay
	            		 */
	            		int delay = 1; //block replacement delay after block break (1ms)
	            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() {
	                        @Override
	                        public void run() {
	                        	Bukkit.getServer().getWorld(e.getBlock().getWorld().getName()).getBlockAt(blockLoc).setType(blockReplaceMaterial); //place block
	                        	
	                        	Config.miningReward(e.getPlayer()); //give player currency
	                        	
	                        	//particle effect
	                        	Location ploc = new Location(e.getBlock().getWorld(),
	                        			
	                        			e.getBlock().getLocation().getX() + 0.5, //particle start position (0.5 mid of block)
	                        			e.getBlock().getLocation().getY() + 0.5,
	                        			e.getBlock().getLocation().getZ() + 0.5);
	                        		
	                        	e.getPlayer().getWorld().spigot().playEffect(ploc
	                        				
	                        			, Effect.EXPLOSION,
	                        			
	                        			1, //id
	                        			1, //data
	                        			0.4f, //offsetX (particle spread)
	                        			0.4f, //offsetY
	                        			0.4f, //offsetZ
	                        			0, //speed
	                        			10, //particleCount (particles that spawn inside the zone)
	                        			30); //radius
	                        }
	                    }, delay);   
    				}
            	}
    		}
    		lw_counter += 2;
    	}
	}
	
	public static void setBlockWorld(World w) { //set world for other classes (MiningZoneSort)
		blockWorld = w;
	}
	public static World getBlockWorld() { //get world for other classes (MiningZoneSort)
		return blockWorld;
	}
	
	public static void setPlayer(Player p) {
		player = p;
	}
	public static Player getPlayer() {
		return player;
	}
	
	public static void setBlockMaterial(Material m) {
		blockMaterial = m;
	}
	public static Material getBlockMaterial() {
		return blockMaterial;
	}
}
