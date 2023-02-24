package blume_system.butcher;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import blume_system.config.Level;
import blume_system.settings.Config;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

public class AnimalReward implements Listener {
	
	/*
	 * reward and animation for animal kill event
	 */
	public static IChatBaseComponent chatSubtitle;
	
	@EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null && !e.getEntity().getKiller().isOp()) { //if player killed the animal
           if (e.getEntity().getType() == Config.aTypeChicken) {
        	   Level.addLevelButcher(Config.aRewardChicken, e.getEntity().getKiller());
        	   chatSubtitle = ChatSerializer.a("{\"text\": \"" +  Config.aChickenBanner + "\"}");
        	   animalAnimation(e.getEntity(), e.getEntity().getKiller());
        	   
           } else if (e.getEntity().getType() == Config.aTypePig) {
        	   Level.addLevelButcher(Config.aRewardPig, e.getEntity().getKiller());
        	   chatSubtitle = ChatSerializer.a("{\"text\": \"" +  Config.aPigBanner + "\"}");
        	   animalAnimation(e.getEntity(), e.getEntity().getKiller());
        	   
           } else if (e.getEntity().getType() == Config.aTypeSheep) {
        	   Level.addLevelButcher(Config.aRewardSheep, e.getEntity().getKiller());
        	   chatSubtitle = ChatSerializer.a("{\"text\": \"" +  Config.aSheepBanner + "\"}");
        	   animalAnimation(e.getEntity(), e.getEntity().getKiller());
        	   
           } else if (e.getEntity().getType() == Config.aTypeCow) {
        	   Level.addLevelButcher(Config.aRewardCow, e.getEntity().getKiller());
        	   chatSubtitle = ChatSerializer.a("{\"text\": \"" +  Config.aCowBanner + "\"}");
        	   animalAnimation(e.getEntity(), e.getEntity().getKiller());
        	   
           }
        }
    }
	
	public void animalAnimation(Entity e, Player p) {
		
   		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + "" + "\"}");
   		
   		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
   		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatSubtitle);
   		PacketPlayOutTitle length = new PacketPlayOutTitle(0, 5, 4);
   		
   		((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
   		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
   		((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
   		
   		//play sound
   		p.playSound(p.getLocation(), Sound.FALL_BIG, 2, 5);
   		
   		if (e.getType() == EntityType.COW) {
   			p.playSound(p.getLocation(), Sound.LEVEL_UP, 4, 5);
   			
   			Location ploc = new Location(e.getWorld(),
   	    			e.getLocation().getX(), //particle start position (0.5 mid of block)
   	    			e.getLocation().getY() + 0.6,
   	    			e.getLocation().getZ());
   	    	e.getWorld().spigot().playEffect(ploc
						   	    			, Effect.COLOURED_DUST,
						   	    			1, //id
						   	    			1, //data
						   	    			0.4f, //offsetX (particle spread)
						   	    			0.4f, //offsetY
						   	    			0.4f, //offsetZ
						   	    			0, //speed
						   	    			30, //particleCount (particles that spawn inside the zone)
						   	    			20); //radius
   		} else {
   			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2, 5);
   			Location ploc = new Location(e.getWorld(),
   	    			e.getLocation().getX(), //particle start position (0.5 mid of block)
   	    			e.getLocation().getY() + 0.4,
   	    			e.getLocation().getZ());
   	    	e.getWorld().spigot().playEffect(ploc
						   	    			, Effect.CRIT,
						   	    			1, //id
						   	    			1, //data
						   	    			0.4f, //offsetX (particle spread)
						   	    			0.4f, //offsetY
						   	    			0.4f, //offsetZ
						   	    			0, //speed
						   	    			20, //particleCount (particles that spawn inside the zone)
						   	    			30); //radius
   		}
	}
}
