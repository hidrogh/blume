package blume_system.config;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class Tablist {
	public static void setTablist(Player p) {
		setPlayername(p);
		
		CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = ChatSerializer.a("{\"text\": \"" + ChatColor.GRAY + "Welcome on " + ChatColor.YELLOW + "Blume\n" + "\"}");
        IChatBaseComponent footer = ChatSerializer.a("{\"text\": \"" + "" + "\"}");
        
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
       
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        connection.sendPacket(packet);
    }
	
	public static void setPlayername(Player p) {
		String rankName = Config.getGlobalRank(p);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() { //has to run a litte bit late because the join event will overwrite
            @Override
            public void run() {
            	p.setPlayerListName(ChatColor.GRAY + "[" + rankName.substring(0, 3) + ChatColor.GRAY + "] " + p.getName());
            }
        }, 1);
	}
}
