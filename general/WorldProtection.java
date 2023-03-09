package blume_system.general;

import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

import blume_system.settings.Config;

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
	
	//STOP ENTITY BREED EVENT
	@EventHandler(priority=EventPriority.MONITOR)
    public void onBreeding(CreatureSpawnEvent e) {
		if (Switches.getStopWPCreatureBreed() == false && e.getSpawnReason().equals(SpawnReason.BREEDING)) {
			e.setCancelled(true);
		} else {
			Switches.setStopWPCreatureBreed(false); //reset
		}
    }
	
	//STOP DRAGON BLOCK DAMAGE
	@EventHandler(priority=EventPriority.MONITOR)
    public void stopDragonDamage(EntityExplodeEvent e) {
	    if (e instanceof EnderDragon) {
	    	if (Switches.getStopWPDragonDamage() == false) {
	    		 e.setCancelled(true);
	    	} else {
	    		Switches.setStopWPDragonDamage(false); //reset
	    	}
	    }
	}
	
	//STOP ENTITY DAMAGE ON VILLAGERS (That got placed by an operator)
	@EventHandler(priority=EventPriority.MONITOR)
    public void stopVillagerDamage(EntityDamageByEntityEvent e) {
	    if (Switches.getStopWPVillagerDamage() == false) {
	    	for (int i = 0; i < Config.villager.length; i++) {
	    		if (e.getEntity().getCustomName() != null) {
	    			if (Config.villager[i].equals(e.getEntity().getCustomName())) {
		    	    	if (!e.getDamager().isOp()) {
		    	    		e.setCancelled(true);
		    	    	}
		    		}
	    		}
	    	}
	    } else {
	    	Switches.setStopWPVillagerDamage(false); //reset
	    }
	}
	
	//STOP VILLAGER SHOP WINDOW OPEN
	@EventHandler(priority=EventPriority.MONITOR)
	public void onVillagerShopOpen(InventoryOpenEvent e){
	    if(e.getInventory().getType() == InventoryType.MERCHANT){
	        e.setCancelled(true);
	    }
	}
}
