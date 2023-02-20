package blume_system.config;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import blume_system.chat.ChatTags;
import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class Rankup {
	
	/*
	 * - ranks are defined in the config
	 * - this class only shows rankup messages
	 */
	
	public Rankup(Player p) {
		String currentStatus = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".status");
		
		if (RankupContainer.getCurrentStatus().equals(currentStatus) ||
			currentStatus == Config.rank1) {
			
			//*** no rankup ***
		} else { //rankup happend
			rankupSequenz(p);
		}
		
		RankupContainer.setCurrentStatus(currentStatus);
	}
	
	public static void rankupSequenz(Player p) {
		String currentStatus = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".status");
		
		p.sendMessage(ChatTags.logBlume() + "Congratulations, you ranked up from " + currentStatus + " to " + Config.getGlobalRank(p).substring(0, 1).toLowerCase() + Config.getGlobalRank(p).substring(1) + ChatColor.GRAY + ".");
		
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + ChatColor.GRAY + "RANK UP" + "\"}");
		IChatBaseComponent chatSubtitle = ChatSerializer.a("{\"text\": \"" +  ChatColor.GRAY + "New rank: " + Config.getGlobalRank(p) + "\"}");
		
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubtitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(10, 120, 50);


		((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
		
		//play sound
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 5); //sound volume, sound pitch (1 normal, higher int = lower sound)
		p.playSound(p.getLocation(), Sound.BLAZE_HIT, 5, 5);
		p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 10, 5);
		
		if (currentStatus == Config.rank7) { //special sound for highest level
			p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 10, 5);
		}
		
		//perticles
		Location ploc = new Location(p.getWorld(),
			
			p.getLocation().getX(), //particle position
			p.getLocation().getY() + 2,
			p.getLocation().getZ());
		
		p.getWorld().spigot().playEffect(ploc
				
			, Effect.FLAME,
			
			1, //id
			1, //data
			1.0f, //offsetX
			1.0f, //offsetY
			1.0f, //offsetZ
			0, //speed
			150, //particleCount (particles that spawn inside the zone)
			10); //radius
	}
}
