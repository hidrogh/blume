package blume_system.settings;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import blume_system.chat.operatorcommands.CommandDeops;
import blume_system.chat.operatorcommands.CommandMakemepro;
import blume_system.chat.operatorcommands.CommandOps;
import blume_system.chat.operatorcommands.CommandRankuptest;
import blume_system.chat.operatorcommands.CommandResetbody;
import blume_system.chat.usercommands.CommandRanks;
import blume_system.chat.usercommands.CommandStats;
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
		
		Main.getPluginInstance().getConfig().set(uuid + ".status", ""); //ranks
		
		Main.getPluginInstance().getConfig().set(uuid + ".warned", 0); //amount of system warning
	}
	
	
	/*
	 * command creation
	 * 
	 * - add in this class: command, class, description
	 * - create class and edit command
	 * - add command to "plugin.yml"
	 */
	public static String userCommands[] = {					"ranks",
															"stats",
															"makemepro",
															"rankuptest",
															"resetbody",
															"ops",
															"deops"
	};

	public static CommandExecutor userCommandClasses[] = {	new CommandRanks(),
		  													new CommandStats(),
		  													new CommandMakemepro(),
		  													new CommandRankuptest(),
		  													new CommandResetbody(),
		  													new CommandOps(),
		  													new CommandDeops()
	};

	public static String userCommandDesk[] = {				"List all user ranks",
															"Your stats",
															"Maxes all stats",
															"Test rankup sequenz",
															"Rest all stats",
															"Makes you op",
															"Makes you deop"
	};
	
	
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
	 * ore banner message
	 */
	public static String bannerCoal = ChatColor.DARK_GRAY + "+" + Integer.toString(reward_coal) + ChatColor.WHITE + " lvl";
	public static String bannerIron = ChatColor.GRAY + "+" + Integer.toString(reward_iron) + ChatColor.WHITE + " lvl";
	public static String bannerGold = ChatColor.GOLD + "+" + Integer.toString(reward_gold) + ChatColor.WHITE + " lvl";
	public static String bannerDiamond = ChatColor.AQUA + "+" + Integer.toString(reward_diamond) + ChatColor.WHITE + " lvl";
	public static String bannerEmerald = ChatColor.GREEN + "+" + Integer.toString(reward_emerald) + ChatColor.WHITE + " lvl";
	
	
	/*
	 * ranks for global xp
	 * 
	 *  - only use small letters
	 */
	public static String rank1 = "newbie"; //0 points at start
	public static ChatColor r1c = ChatColor.GRAY; //color of rank
	
	public static String rank2 = "explorer";
	public static int r2_points = 25; //how many points are needed
	public static ChatColor r2c = ChatColor.GRAY;
	
	public static String rank3 = "specialist";
	public static int r3_points = 200;
	public static ChatColor r3c = ChatColor.BLUE;
	
	public static String rank4 = "professional";
	public static int r4_points = 1000;
	public static ChatColor r4c = ChatColor.BLUE;
	
	public static String rank5 = "master";
	public static int r5_points = 5000;
	public static ChatColor r5c = ChatColor.GREEN;
	
	public static String rank6 = "champion";
	public static int r6_points = 15000;
	public static ChatColor r6c = ChatColor.GREEN;
	
	public static String rank7 = "emperor";
	public static int r7_points = 50000;
	public static ChatColor r7c = ChatColor.LIGHT_PURPLE;
	
	
	/*
	 * animal spawning zones
	 * 
	 * - one row equals one zone
	 * - enter from: x,y,z   and   to: x,y,z (sorting is not needed)
	 * - you can add infinite rows (zones)
	 * - name each zone at the end
	 */
	public static int[] getAnimalSpawningZones() {
		int[] animalZones = {
				7,56,2,				13,50,-3, //animal spawning zone 1
				0,0,0,				0,0,0, //animal spawning zone 2
				0,0,0,				0,0,0 //animal spawning zone 3
		};
    	return animalZones;
    }
	//animal spawn position (should equal location amount)
	public static int[] getAnimalSpawningPosition() {
		int[] animalPosition = {
				7,56,2,				13,50,-3, //location for spawning zone 1
				0,0,0,				0,0,0, //location for spawning zone 2
				0,0,0,				0,0,0  //location for spawning zone 3
		};
    	return animalPosition;
    }
	
	
	/*
	 * scoreboard format
	 */
	public static String sbRank = "Rank: ";
	public static String sbMoney = ChatColor.WHITE + "Money: " + ChatColor.YELLOW;
	public static String sbLevel = ChatColor.WHITE + "Level: " + ChatColor.YELLOW;
	public static String sbNextLevel = ChatColor.WHITE + "Next lvl in: ";
	public static String sbNextMaxLevel = ChatColor.RED + "§lMAX LVL REACHED"; //next lvl message on max lvl
	
	
	/*
	 * max number of Level and Money
	 */
	public static int maxMoneyAmount = 1000000000;
	public static int maxLevelAmount = 1000000000;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//-----------------------------------------------------------------------------
//					OVERFLOW
//-----------------------------------------------------------------------------
	public static void miningReward(Player p) {
		if (Mining.getBlockMaterial() == Material.COAL_ORE) {
			RewardMessage.setMessage(bannerCoal, p); //send message to player
			Level.addLevelMiner(reward_coal, p);
		} else if (Mining.getBlockMaterial() == Material.IRON_ORE) {
			RewardMessage.setMessage(bannerIron, p);
			Level.addLevelMiner(reward_iron, p);
		} else if (Mining.getBlockMaterial() == Material.GOLD_ORE) {
			RewardMessage.setMessage(bannerGold, p);
			Level.addLevelMiner(reward_gold, p);
		} else if (Mining.getBlockMaterial() == Material.DIAMOND_ORE) {
			RewardMessage.setMessage(bannerDiamond, p);
			Level.addLevelMiner(reward_diamond, p);
		} else if (Mining.getBlockMaterial() == Material.EMERALD_ORE) {
			RewardMessage.setMessage(bannerEmerald, p);
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
			rank = r7c + rank7.substring(0, 1).toUpperCase() + rank7.substring(1);
		} else if (r6_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r6c + rank6.substring(0, 1).toUpperCase() + rank6.substring(1);
		} else if (r5_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r5c + rank5.substring(0, 1).toUpperCase() + rank5.substring(1);
		} else if (r4_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r4c + rank4.substring(0, 1).toUpperCase() + rank4.substring(1);
		} else if (r3_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r3c + rank3.substring(0, 1).toUpperCase() + rank3.substring(1);
		} else if (r2_points <= Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r2c + rank2.substring(0, 1).toUpperCase() + rank2.substring(1);
		} else if (r2_points > Integer.parseInt(Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global"))) {
			rank = r1c + rank1.substring(0, 1).toUpperCase() + rank1.substring(1);
		}
		
		return rank;
	}
}
