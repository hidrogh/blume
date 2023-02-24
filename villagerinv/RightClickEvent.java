package blume_system.villagerinv;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import blume_system.settings.Config;

public class RightClickEvent implements Listener {
	@EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent e){ //on rightclick
		Player p = e.getPlayer();
		
		if (e.getRightClicked().getType() == EntityType.VILLAGER) {
			if (e.getRightClicked().getCustomName().equals(Config.villager[0])) { //open buyer inv
				BuyerInv.openInv(p);
			} else if (e.getRightClicked().getCustomName().equals(Config.villager[1])) { //open seller inv
				SellerInv.openInv(p);
			} else if (e.getRightClicked().getCustomName().equals(Config.villager[2])) { //open bank inv
				BankInv.openInv(p);
			} else if (e.getRightClicked().getCustomName().equals(Config.villager[3])) { //open level inv
				LevelInv.openInv(p);
			}
		}
    }
}
