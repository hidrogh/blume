package blume_system.butcher;

import blume_system.general.Main;
import blume_system.settings.Config;

public class AnimalController {
	public static void setChickenAmount(int i) { //chicken
		setAmount(i, Config.aConfigNameChicken);
	}
	public static int getChickenAmount() {
		return getAmount(Config.aConfigNameChicken);
	}
	
	public static void setPigAmount(int i) { //pig
		setAmount(i, Config.aConfigNamePig);
	}
	public static int getPigAmount() {
		return getAmount(Config.aConfigNamePig);
	}
	
	public static void setSheepAmount(int i) { //sheep
		setAmount(i, Config.aConfigNameSheep);
	}
	public static int getSheepAmount() {
		return getAmount(Config.aConfigNameSheep);
	}
	
	public static void setCowAmount(int i) { //cow
		setAmount(i, Config.aConfigNameCow);
	}
	public static int getCowAmount() {
		return getAmount(Config.aConfigNameCow);
	}
	
	//write/read from and to config file
	public static void setAmount(int amount, String animalType) { //set amount of x animal in the world
		Main.getPluginInstance().getConfig().set("world.animals." + animalType, amount);
	}
	public static int getAmount(String animalType) { //get amount of x animal in the world
		return Integer.parseInt(Main.getPluginInstance().getConfig().getString("world.animals." + animalType));
	}
}
