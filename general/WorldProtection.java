package fr.hidro.blume.general;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

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
}
