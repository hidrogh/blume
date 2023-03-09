package blume_system.villagerinv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class SellerInv implements Listener {
	public static void openInv(Player p) {
		Inventory myInventory = Bukkit.createInventory(null, 9, ChatColor.BLACK + "Seller");
		
		myInventory.setItem(0, new ItemStack(Material.DIRT, 1));
		
		p.openInventory(myInventory);
	}
	
	@EventHandler
    public void onBuyerClick(InventoryClickEvent e) {
		System.out.println("asdasdasd");
	}
}
