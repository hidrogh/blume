package blume_system.general;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class WorldProtection implements Listener {
	
	/*
	 * world protection class
	 * 
	 * - if other classes wont stop the world protection, it will be active
	 * - event will be not last if the "Switches" class will get set inside a delay, the delay will take longer and this event will be prioritised
	 * - switches class needs to be used to disable global world protection
	 */
	
	//BLOCK BREAKING
	@EventHandler(priority=EventPriority.MONITOR) //this event will run last
	public void onBlockBreak(BlockBreakEvent e) {
		if (Switches.getStopWPBlockBreak() == false && !e.getPlayer().isOp()) {
			e.setCancelled(true);
		} else {
			Switches.setStopWPBlockBreak(false); //reset
		}
	}
	
	//BLOCK PLACING
	@EventHandler(priority=EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (Switches.getStopWPBlockPlace() == false && !e.getPlayer().isOp()) {
			e.setCancelled(true);
		} else {
			Switches.setStopWPBlockPlace(false); //reset
		}
	}
	
	//ITEM SPAWN (BLOCK BREAK...)
	@EventHandler(priority=EventPriority.MONITOR)
	public void onItemSpawn(ItemSpawnEvent e) {
		if (Switches.getStopWPItemSpawn() == false) {
			e.setCancelled(true);
		} else {
			Switches.setStopWPItemSpawn(false); //reset
		}
	}
	
	//WORLD XP DROP (OVEN, ORES...)
	@EventHandler(priority=EventPriority.MONITOR)
	public void onBlockExp(BlockExpEvent e) {
		if (Switches.getStopWPBlockExp() == false) {
			e.setExpToDrop(0);
		} else {
			Switches.setStopWPBlockExp(false); //reset
		}
	}
	
	//PLAYER ITEM DROP
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if (Switches.getStopWPPlayerDropItem() == false) {
			if (!e.getPlayer().isOp()) {
				e.setCancelled(true);
			}
		} else {
			Switches.setStopWPPlayerDropItem(false); //reset
		}
	}
}
