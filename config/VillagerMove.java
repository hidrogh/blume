package blume_system.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import blume_system.general.Main;
import blume_system.settings.Config;

public class VillagerMove {

	public VillagerMove() {
		for (Entity e : Bukkit.getWorld(Config.getWorldName()).getEntities()) {
			if (EntityType.VILLAGER == e.getType()) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPluginInstance(), new Runnable() { // needs																					// delay
					@Override
					public void run() {
						for (int i = 0; i < Config.villager.length; i++) {
							if (e.getCustomName() != null) {
								if (e.getCustomName().equals(Config.villager[i])) {

									boolean opener = false;
									String selected = null;

									if (i == 0 && ConfigSettings.getVillagerBuyer() != null) {
										opener = true;
										selected = ConfigSettings.getVillagerBuyer();
									} else if (i == 1 && ConfigSettings.getVillagerSeller() != null) {
										opener = true;
										selected = ConfigSettings.getVillagerSeller();
									} else if (i == 2 && ConfigSettings.getVillagerBank() != null) {
										opener = true;
										selected = ConfigSettings.getVillagerBank();
									} else if (i == 3 && ConfigSettings.getVillagerLevel() != null) {
										opener = true;
										selected = ConfigSettings.getVillagerLevel();
									}

									if (opener == true) {
										String XYZstring = selected; // makes the string out of the config file (s,s,s)
																		// to x, y, z ints
										int x = 123456789;
										int y = 123456789;
										int z = 123456789;

										for (int i2 = 0; i2 < XYZstring.length(); i2++) {
											if (XYZstring.substring(i2, i2 + 1).equals(",")) {
												if (x == 123456789) {
													x = Integer.parseInt(XYZstring.substring(0, i2));
												} else if (y == 123456789) {
													y = Integer.parseInt(XYZstring.substring(Integer.toString(x).length() + 1, i2));
													z = Integer.parseInt(XYZstring.substring(i2 + 1, XYZstring.length()));
												}
											}
										}
										Location newLoc;
										if (Config.villagerLocBuff == true) {
											newLoc = new Location(Bukkit.getWorld(Config.getWorldName()), x + 0.5, y, z + 0.5);
										} else {
											newLoc = new Location(Bukkit.getWorld(Config.getWorldName()), x, y, z);
										}
										
										((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, 255));
										e.teleport(newLoc);
									}
								}
							}
						}
					}
				}, 0);
			}
		}

	}
}
