package blume_system.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import blume_system.config.Level;
import blume_system.general.Main;
import blume_system.mining.Mining;
import blume_system.mining.RewardMessage;
import net.md_5.bungee.api.ChatColor;

public class Config {
	
	/*
	 * SETTINGS
	 */
	
	
	/*
	 * world name
	 */
	public static String getWorldName() {
		return "world"; //world folder name
	}
	
	
	/*
	 * add datapoints to player inside config file
	 * 
	 * - players need to rejoin to add later on addet columns
	 */
	public static void setNewPlayerData(Player p) {
		String uuid = "players.uuid-" + p.getUniqueId().toString();
		
		Main.getPluginInstance().getConfig().set(uuid + ".level_global", 0); //global level
		Main.getPluginInstance().getConfig().set(uuid + ".level_miner", 0); //miner level
		Main.getPluginInstance().getConfig().set(uuid + ".level_farmer", 0); //farmer level
		Main.getPluginInstance().getConfig().set(uuid + ".level_butcher", 0); //butcher level
		Main.getPluginInstance().getConfig().set(uuid + ".level_warrior", 0); //warrior level
		
		Main.getPluginInstance().getConfig().set(uuid + ".currency", 0); //currency amount (xp bar)
		
		Main.getPluginInstance().getConfig().set(uuid + ".status", ""); //rank, banned, warned
	}
	
	
	/*
	 * mining zones, mining world event zones
	 * 
	 * - one row equals one zone
	 * - enter from: x,y,z   and   to: x,y,z (sorting is not needed)
	 * - you can add infinite rows (zones)
	 * - name each zone at the end
	 */
	public static boolean userZones = true;
	
	public static int[] getMiningZones() {
		int[] miningZones = {
				7,56,2,				13,50,-3, //mining zone 1
				0,0,0,				0,0,0, //mining zone 2
				0,0,0,				0,0,0 //mining zone 3
		};
    	return miningZones;
    }
	
	
	/*
	 * chance for each block spawn in the mining zones
	 * 
	 * - does not need to add up to 100 because values are getting normed later on
	 */
	public static int[] getMiningBlockProb() {
		int[] blockProb = {
				80, //stone
				20, //coal ore
				15, //iron ore
				10, //gold ore
				5, //diamond ore
				1, //emerald ore
		};
		return blockProb;
	}
	
	
	/*
	 * mining reward amount
	 */
	public static int reward_coal = 1;
	public static int reward_iron = 3;
	public static int reward_gold = 6;
	public static int reward_diamond = 10;
	public static int reward_emerald = 15;
	
	
	/*
	 * ranks for global xp
	 * 
	 *  - only use small letters
	 */
	public static String rank1 = "newbie"; //0 points at start
	
	public static String rank2 = "villager";
	public static int r2_points = 25; //how many points are needed
	
	public static String rank3 = "worker";
	public static int r3_points = 200;
	
	public static String rank4 = "professional";
	public static int r4_points = 1000;
	
	public static String rank5 = "master";
	public static int r5_points = 5000;
	
	public static String rank6 = "lord";
	public static int r6_points = 15000;
	
	public static String rank7 = "king";
	public static int r7_points = 50000;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
//-----------------------------------------------------------------------------
//					OVERFLOW
//-----------------------------------------------------------------------------
	public static void miningReward(Player p) {
		if (Mining.getBlockMaterial() == Material.COAL_ORE) {
			RewardMessage.setMessage(ChatColor.DARK_GRAY + "+" + Integer.toString(reward_coal) + ChatColor.WHITE + " Miner Level", p); //send message to player
			Level.addLevelMiner(reward_coal, p);
		} else if (Mining.getBlockMaterial() == Material.IRON_ORE) {
			RewardMessage.setMessage(ChatColor.GRAY + "+" + Integer.toString(reward_iron) + ChatColor.WHITE + " Miner Level", p);
			Level.addLevelMiner(reward_iron, p);
		} else if (Mining.getBlockMaterial() == Material.GOLD_ORE) {
			RewardMessage.setMessage(ChatColor.GOLD + "+" + Integer.toString(reward_gold) + ChatColor.WHITE + " Miner Level", p);
			Level.addLevelMiner(reward_gold, p);
		} else if (Mining.getBlockMaterial() == Material.DIAMOND_ORE) {
			RewardMessage.setMessage(ChatColor.AQUA + "+" + Integer.toString(reward_diamond) + ChatColor.WHITE + " Miner Level", p);
			Level.addLevelMiner(reward_diamond, p);
		} else if (Mining.getBlockMaterial() == Material.EMERALD_ORE) {
			RewardMessage.setMessage(ChatColor.GREEN + "+" + Integer.toString(reward_emerald) + ChatColor.WHITE + " Miner Level", p);
			Level.addLevelMiner(reward_emerald, p);
		}
	}
	
	
	public static void refreshGlobalRank(Player p) {
		if (p.isOp()) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", "operator");
		} else if (r7_points < Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank7);
		} else if (r6_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank6);
		} else if (r5_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank5);
		} else if (r4_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank4);
		} else if (r3_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank3);
		} else if (r2_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank2);
		} else if (r2_points > Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			Main.getPluginInstance().getConfig().set("players.uuid-" + p.getUniqueId().toString() + ".status", rank1);
		}
	}
	public static String getGlobalRank(Player p) {
		String rank = "";
		
		if (p.isOp()) {
			rank = ChatColor.RED  + "Operator";
		} else if (r7_points < Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.YELLOW + rank7.substring(0, 1).toUpperCase() + rank7.substring(1);
		} else if (r6_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.GREEN + rank6.substring(0, 1).toUpperCase() + rank6.substring(1);
		} else if (r5_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.GREEN + rank5.substring(0, 1).toUpperCase() + rank5.substring(1);
		} else if (r4_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.BLUE + rank4.substring(0, 1).toUpperCase() + rank4.substring(1);
		} else if (r3_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.BLUE + rank3.substring(0, 1).toUpperCase() + rank3.substring(1);
		} else if (r2_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.GRAY + rank2.substring(0, 1).toUpperCase() + rank2.substring(1);
		} else if (r2_points > Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = ChatColor.GRAY + rank1.substring(0, 1).toUpperCase() + rank1.substring(1);
		}
		
		return rank;
	}
}
