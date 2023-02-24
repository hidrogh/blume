package blume_system.villagerinv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class BankInv {
	public static void openInv(Player p) {
		Inventory myInventory = Bukkit.createInventory(null, 9, ChatColor.BLACK + "Bank");
		
		myInventory.setItem(0, new ItemStack(Material.DIRT, 1));
		myInventory.setItem(8, new ItemStack(Material.GOLD_BLOCK, 1));
		
		p.openInventory(myInventory);
		
		//helpful link
		//https://bukkit.org/threads/tutorial-create-a-inventory-menu.173571/
	}
}
