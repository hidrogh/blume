package blume_system.general;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import blume_system.butcher.AnimalEvent;
import blume_system.butcher.AnimalReward;
import blume_system.butcher.AnimalZoneSort;
import blume_system.chat.CommandCreator;
import blume_system.chat.JoinLeave;
import blume_system.chat.PlayerWrite;
import blume_system.config.ConfigSettings;
import blume_system.config.DisconnectMessages;
import blume_system.config.PlayerScoreboard;
import blume_system.mining.Mining;
import blume_system.mining.MiningZoneSort;
import blume_system.settings.Config;

public class Main extends JavaPlugin {
	
	/*
	 * main class
	 */
	
	public static Plugin pluginInstance;
	  
    public void onEnable() {
    	startMessage(); //show console start message
    	
    	pluginInstance = this; //identify plugin instance (use "JavaPlugin" on other classes)
    	
    	getPluginInstance().saveDefaultConfig(); //create config file to save user data
    	
    	DisconnectMessages.kickOnReload(); //kick on reload
    	
    	setClasses(); //run classes
    	setListeners(); //set listeners
    	setGamerules(); //set gamerules
    	setCommands(); //set commands
    	
    	ConfigSettings.addWorldSettings(); //add world settings inside the config
    }
    
    public void onDisable() {
    	System.out.print(Log.logInfo() + "blume_system now offline");
    }
    
    
    
    
    
    
    
    
    
    
//-----------------------------------------------------------------------------
//	OVERFLOW
//-----------------------------------------------------------------------------
    public static Plugin getPluginInstance() { //get "JavaPlugin" instance on other classes with (Main.getPluginInstance();)
    	return pluginInstance;
    }
    
    public void setGamerules() {
    	System.out.print(Log.logInfo() + "world gamerules set");
    	
    	getPluginInstance().getServer().getWorld(Config.getWorldName()).setGameRuleValue("keepInventory", "true");
    	getPluginInstance().getServer().getWorld(Config.getWorldName()).setGameRuleValue("doMobLoot", "false");
    }
    
    public void setCommands() {
    	for (int i = 0; i < CommandCreator.userCommands.length; i++) {
        	CommandCreator.setCommandPos(i);
        		
        	this.getCommand(CommandCreator.getCommands()).setExecutor(CommandCreator.getCommandClasses());
    	}
    }
    
    public void setListeners() {
    	getServer().getPluginManager().registerEvents(new Mining(), this);
    	getServer().getPluginManager().registerEvents(new WorldProtection(), this);
    	getServer().getPluginManager().registerEvents(new JoinLeave(), this);
    	getServer().getPluginManager().registerEvents(new PlayerWrite(), this);
    	getServer().getPluginManager().registerEvents(new AnimalEvent(), this);
    	getServer().getPluginManager().registerEvents(new AnimalReward(), this);
    }
    
    public void setClasses() {
    	new Updater();
    	new MiningZoneSort();
    	new AnimalZoneSort();
    }
    
    public void startMessage() {
    	System.out.print(Log.logInfo() + "blume_system now online");
    	System.out.print(Log.logInfo() + "	version: 1.0");
    	System.out.print(Log.logInfo() + "	spigot version: 1.8");
    }
}
