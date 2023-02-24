package blume_system.config;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Controller {
	
	/*
	 * global controller class
	 */
	
	//give item to player with max 64
	public static void giveItem64Max(Material m, Player p, String name, String desc) {
		int counter2 = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i == null) {
				counter2 = 1;
			} else if (i.getItemMeta().getDisplayName() != null) {
				if (i.getItemMeta().getDisplayName().equals(name)) {
					counter2 = 1;
				}
			}
		}
		if (counter2 == 1) { //if inventory is not full
			int counter = 0;
		    for (ItemStack i : p.getInventory().getContents()) {
		    	if (i != null) {
			    	if (i.getItemMeta().getDisplayName() != null) {
			            if(i.getItemMeta().getDisplayName().equals(name)) {
			            	counter += i.getAmount();
			            }
			    	}
		    	}
	        }
			if (counter < 64) {
				ItemStack item = new ItemStack(m, 1);
				ItemMeta im = item.getItemMeta();
				im.setDisplayName(name);
				List<String> lore = Arrays.asList(desc);
				im.setLore(lore);
				
				item.setItemMeta(im);
				
				p.getInventory().addItem(item);
			} else {
				//p.sendMessage(Config.invFullMes);
			}
		}
	}
}
