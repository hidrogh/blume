package fr.hidro.blume.general;

import fr.hidro.blume.settings.Config;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

public class WorldProtection implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {
        if (!Switches.getStopWPBlockBreak() && !e.getPlayer().isOp()) {
            e.setCancelled(true);
        } else {
            Switches.setStopWPBlockBreak(false);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!Switches.getStopWPBlockPlace() && !e.getPlayer().isOp()) {
            e.setCancelled(true);
        } else {
            Switches.setStopWPBlockPlace(false);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemSpawn(BlockPlaceEvent e) {
        if (!Switches.getStopWPItemSpawn()) {
            e.setCancelled(true);
        } else {
            Switches.setStopWPItemSpawn(false);
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onBlockExp(BlockExpEvent e) {
        if (!Switches.getStopWPBlockExp()) {
            e.setExpToDrop(0);
        } else {
            Switches.setStopWPBlockExp(false); //reset
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (!Switches.getStopWPPlayerDropItem()) {
            if (!e.getPlayer().isOp()) {
                e.setCancelled(true);
            }
        } else {
            Switches.setStopWPPlayerDropItem(false); //reset
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onBreeding(CreatureSpawnEvent e) {
        if (!Switches.getStopWPCreatureBreed() && e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BREEDING)) {
            e.setCancelled(true);
        } else {
            Switches.setStopWPCreatureBreed(false); //reset
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void stopDragonDamage(EntityExplodeEvent e) {
        if (e instanceof EnderDragon) {
            if (!Switches.getStopWPDragonDamage()) {
                e.setCancelled(true);
            } else {
                Switches.setStopWPDragonDamage(false); //reset
            }
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void stopVillagerDamage(EntityDamageByEntityEvent e) {
        if (!Switches.getStopWPVillagerDamage()) {
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

    @EventHandler(priority=EventPriority.MONITOR)
    public void onVillagerShopOpen(InventoryOpenEvent e){
        if(e.getInventory().getType() == InventoryType.MERCHANT){
            e.setCancelled(true);
        }
    }
}
