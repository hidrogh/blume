package blume_system.config;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class DisconnectMessages {
	
	/*
	 * custom disconnect messages
	 * 
	 * - enter "getInfoPart" firstly
	 * - define: p for player and true/false for kicked or banned
	 */
	static String typeDisc = "NONE";
	
    public static void kickOnReload() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (!p.isOp()) {
                p.kickPlayer(getInfoPart(p, true) + "You were kicked due to a system reboot.\nYou can rejoin shortly.");
            }
        }
    }
    public static void kickOnOp(Player p) { //if player goes from op to deop or deop to op
        p.kickPlayer(getInfoPart(p, true) + "You were kicked due to a permission change.\nPlease rejoin.");
    }
    public static void kickForError(Player p) { //do not use - for special purpose
        p.kickPlayer(getInfoPart(p, true) + "You were kicked due to a system error.\nYour money and level stats got reset.");
    }
    public static void banned(Player p) {
        p.kickPlayer(getInfoPart(p, false) + "You were banned.");
    }
    
    
    
    
    
    public static String getInfoPart(Player p, Boolean type) {
    	if (type == true) {
    		typeDisc = "kicked";
    	} else {
    		typeDisc = "banned";
    	}
    	
    	return ChatColor.YELLOW + "§lBLUME" + "\n" +
			   "" + "\n" +
			   ChatColor.GRAY + "Type: " + ChatColor.WHITE + typeDisc + "\n" +
			   ChatColor.GRAY + "Username: " + ChatColor.WHITE + p.getName() + "\n" +
			   ChatColor.GRAY + "UUID: " + ChatColor.WHITE + p.getUniqueId() + "\n" +
			   "" + "\n" + ChatColor.RED;
    }
}
