package blume_system.villagerinv;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blume_system.chat.ChatTags;
import blume_system.config.Currency;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class BuyerInv implements Listener {
	
	public static String invName = ChatColor.BLACK + "Buyer"; //inventory name
	
	public static int invsize;
	public static String invname;
	public static Material[] item = new Material[55]; //do not change the 55
	public static int[] amount = new int[55];
	public static int[] position = new int[55];
	public static String[] name = new String[55];
	public static DyeColor[] color = new DyeColor[55];
	
	public static List<String>[] descList = new List[56];
	public static int currentPos = 0;
	
	public static String breaker = "#";
	
	public static boolean allCounter = false;
	public static boolean allCounter2 = false;
	
	public static void openInv(Player p) {
		currentPos = 0; //reset after each inventory open
		
		invsize = 54; //item size (9x max 6)
		invname = ChatColor.BLACK + "Buyer"; //item name
		
		//invAddItem: item, amount, position, name, description, color (user PINK if not needed, chatcolors need to be defined again after the #)
		//invAddItemDesc: add item description (use # to use another row)
		//
		//invAddItem(Material.STAINED_GLASS_PANE, 1, 0, "", DyeColor.GRAY);
		//invAddItemDesc("");
		//
		
		for (int i = 0; i < 9; i++) {
			invAddItem(Material.STAINED_GLASS_PANE, 1, i, " ", DyeColor.BLACK);
			invAddItemDesc("");
		}
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 9, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 10, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 11, ChatColor.GREEN + "Sell everything", DyeColor.LIME);
		invAddItemDesc(ChatColor.GRAY + "Sell all of your items at once##" + ChatColor.RED + "§l[CLICK TO SELL]");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 12, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 13, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 14, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 15, ChatColor.RED + "Cancel", DyeColor.RED);
		invAddItemDesc(ChatColor.GRAY + "Cancel this process");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 16, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 17, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		for (int i = 17; i < 27; i++) {
			invAddItem(Material.STAINED_GLASS_PANE, 1, i, " ", DyeColor.BLACK);
			invAddItemDesc("");
		}
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 27, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.PORK, 1, 28, ChatColor.WHITE + "§rSell: " + Config.aRewardItemName, DyeColor.PINK);
		invAddItemDesc(ChatColor.GRAY + "Selling price: " + ChatColor.YELLOW + Config.sPriceMeat + "$##" + ChatColor.RED + "§l[CLICK TO SELL]");
		
		invAddItem(Material.CLAY_BALL, 1, 29, ChatColor.WHITE + "§rSell: " + Config.minerItemName, DyeColor.PINK);
		invAddItemDesc(ChatColor.GRAY + "Selling price: " + ChatColor.YELLOW + Config.sPriceOre + "$##" + ChatColor.RED + "§l[CLICK TO SELL]");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 35, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 36, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		invAddItem(Material.STAINED_GLASS_PANE, 1, 44, " ", DyeColor.BLACK);
		invAddItemDesc("");
		
		for (int i = 45; i < 54; i++) {
			invAddItem(Material.STAINED_GLASS_PANE, 1, i, " ", DyeColor.BLACK);
			invAddItemDesc("");
		}
		
		
		
		addToInventory(p);
	}
	
	@EventHandler
    public void onBuyerClick(InventoryClickEvent e) {
		if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
			String playerInvTitle = e.getInventory().getName();
			if (e.getInventory().getName().equals(ChatColor.BLACK + "Buyer")) {
				if (e.getClickedInventory().getTitle().equals(playerInvTitle)) { //villager inventory
					
					//list of all items that should not be clicked
					if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
						if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Sell everything")) {
							
							sellItemOre(e.getWhoClicked(), false); //false if sell everything
							sellItemMeat(e.getWhoClicked(), false);
							
							if (allCounter = true && allCounter2 == false) {
								e.getWhoClicked().sendMessage(Config.messageNoMoney);
							} else {
								Player p = (Player) e.getWhoClicked();
								p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 10, 5);
								p.playSound(p.getLocation(), Sound.VILLAGER_YES, 10, 5);
								p.playSound(p.getLocation(), Sound.BLAZE_HIT, 10, 5);
							}
							allCounter = false;
							allCounter2 = false;
							
							e.setCancelled(true);
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "§rSell: " + Config.minerItemName)) {
							
							sellItemOre(e.getWhoClicked(), true);
							
							e.setCancelled(true);
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "§rSell: " + Config.aRewardItemName)) {
					        
							sellItemMeat(e.getWhoClicked(),true);
							
							e.setCancelled(true);
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Cancel")) {
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
							e.setCancelled(true);
						}
					}
					
				} else { //own inventory clicks
					
				}
			}
		}
    }
	
	public static void invAddItem(Material m, int a, int p, String n, DyeColor c) {
		item[currentPos] = m;
		amount[currentPos] = a;
		position[currentPos] = p;
		name[currentPos] = n;
		color[currentPos] = c;
		currentPos++;
	}
	
	public static void invAddItemDesc(String desc) {
		ArrayList<String> descListItem = new ArrayList<String>();
		int amountBreaker = 0;
		for (int i = 0; i < desc.length(); i++) {
			if (desc.substring(i, i+1).equals(breaker)) {
				amountBreaker++;
			}
		}
		if (desc.length() > 0 && amountBreaker != 0) { //its not a feature it is a bug
			desc = desc + desc.substring(desc.length()-1, desc.length());
		}
		if (amountBreaker == 0) { //one line
			descListItem.add(desc);
		} else { //multiple lines
			for (int i = 0; i < desc.length(); i++) {
				if (desc.substring(i, i+1).equals(breaker) || desc.length()-1 == i) {
					for (int i2 = 0; i2 < 200; i2++) {
						if (i-1-i2 > 0) {
							if (desc.substring(i-1-i2, i-i2).equals(breaker)) { //rest of the rows
								int a = i-i2;
								descListItem.add(desc.substring(a, i));
								break;
							}
						} else {
							if (i2 == 199) {
								descListItem.add(desc.substring(0, i));
							}
						}
					}
				}
			}
		}
		descList[currentPos] = descListItem;
	}
	
	public static void addToInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, invsize, invname); //add inventory
		
		for (int i = 0; i < item.length; i++) { //add all items
			if (item[i] != null) {
				ItemStack invitem;
				if (color[i].equals(DyeColor.PINK)) {
					invitem = new ItemStack(item[i], amount[i]);
				} else {
					invitem = new ItemStack(item[i], amount[i], color[i].getData());
				}
				
				ItemMeta itemmeta = invitem.getItemMeta();
				itemmeta.setDisplayName(name[i]);
				itemmeta.setLore(descList[i+1]);
				invitem.setItemMeta(itemmeta);
				
				inv.setItem(position[i], new ItemStack(invitem));
			}
		}
		
		p.openInventory(inv);
	}
	
	public static void sellItemOre(HumanEntity humanEntity, Boolean b) {
		int oreAmount = 0;
		for(ItemStack inven : humanEntity.getInventory().getContents()){
			if (inven != null && inven.getItemMeta() != null && inven.getItemMeta().getDisplayName() != null) {
				if (inven.getItemMeta().getDisplayName().equals(Config.minerItemName)){
					oreAmount += inven.getAmount();
					humanEntity.getInventory().removeItem(inven);
		        }
			}
		}
		if (b == true) {
			if (oreAmount == 0) {
				humanEntity.sendMessage(Config.messageNoMoney);
			} else {
				Player p = (Player) humanEntity;
				p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 10, 5);
				p.playSound(p.getLocation(), Sound.VILLAGER_YES, 10, 5);
				p.playSound(p.getLocation(), Sound.BLAZE_HIT, 10, 5);
				humanEntity.sendMessage(ChatTags.logBlume() + "You have sold " + ChatColor.YELLOW + oreAmount + "x "+ ChatColor.YELLOW + "ores " + ChatColor.GRAY + "for " + ChatColor.YELLOW + oreAmount * Integer.parseInt(Config.sPriceOre) + "$");
			}
		} else { //for everything to sell
			if (oreAmount != 0) {
				humanEntity.sendMessage(ChatTags.logBlume() + "You have sold " + ChatColor.YELLOW + oreAmount + "x "+ ChatColor.YELLOW + "ores " + ChatColor.GRAY + "for " + ChatColor.YELLOW + oreAmount * Integer.parseInt(Config.sPriceOre) + "$");
				allCounter2 = true;
			} else {
				allCounter = true;
			}
		}
		humanEntity.closeInventory();
		Currency.addCurrency(oreAmount * Integer.parseInt(Config.sPriceOre), (Player) humanEntity);
	}
	
	public static void sellItemMeat(HumanEntity humanEntity, Boolean b) {
		int meatAmount = 0;
		for(ItemStack inven : humanEntity.getInventory().getContents()){
			if (inven != null && inven.getItemMeta() != null && inven.getItemMeta().getDisplayName() != null) {
				if (inven.getItemMeta().getDisplayName().equals(Config.aRewardItemName)){
		            meatAmount += inven.getAmount();
		            humanEntity.getInventory().removeItem(inven);
		        }
			}
		} 
		if (b == true) {
			if (meatAmount == 0) {
				humanEntity.sendMessage(Config.messageNoMoney);
			} else {
				Player p = (Player) humanEntity;
				p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 10, 5);
				p.playSound(p.getLocation(), Sound.VILLAGER_YES, 10, 5);
				p.playSound(p.getLocation(), Sound.BLAZE_HIT, 10, 5);
				humanEntity.sendMessage(ChatTags.logBlume() + "You have sold " + ChatColor.YELLOW + meatAmount + "x "+ ChatColor.YELLOW + "meat " + ChatColor.GRAY + "for " + ChatColor.YELLOW + meatAmount * Integer.parseInt(Config.sPriceMeat) + "$");
			}
		} else { //for everything to sell
			if (meatAmount != 0) {
				humanEntity.sendMessage(ChatTags.logBlume() + "You have sold " + ChatColor.YELLOW + meatAmount + "x "+ ChatColor.YELLOW + "meat " + ChatColor.GRAY + "for " + ChatColor.YELLOW + meatAmount * Integer.parseInt(Config.sPriceMeat) + "$");
				allCounter2 = true;
			} else {
				allCounter = true;
			}
		}
		
		humanEntity.closeInventory();
		Currency.addCurrency(meatAmount * Integer.parseInt(Config.sPriceMeat), (Player) humanEntity);
	}
}
