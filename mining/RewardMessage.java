package blume_system.mining;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import blume_system.settings.Config;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class RewardMessage {
	
	/*
	 * message, sound for mining
	 */
	
	public static void setMessage(String message, Player p) {
		//show title
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + "" + "\"}");
		IChatBaseComponent chatSubtitle = ChatSerializer.a("{\"text\": \"" +  message + "\"}");
		
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubtitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(0, 3, 5);


		((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
		
		//play sound
		
		if (message == Config.bannerEmerald) {
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 5);
		} else {
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 5); //sound volume, sound pitch (1 normal, higher int = lower sound)
		}
	
	}
}
