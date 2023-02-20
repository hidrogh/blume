package blume_system.chat;

import net.md_5.bungee.api.ChatColor;

public class ChatTags {
	/*
	 * chat syntax
	 */
	
	public static String logBlume() {
		return ChatColor.GRAY + "[ " + ChatColor.YELLOW + "BLUME " + ChatColor.GRAY + "] ";
	}
	
	public static String logSystem() {
		return ChatColor.GRAY + "[ " + ChatColor.DARK_RED + "SYSTEM " + ChatColor.GRAY + "] " + ChatColor.RED;
	}
}
