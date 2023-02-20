package blume_system.general;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import blume_system.chat.JoinLeave;
import blume_system.chat.PlayerWrite;
import blume_system.chat.operatorcommands.CommandMakemepro;
import blume_system.chat.operatorcommands.CommandRankuptest;
import blume_system.chat.operatorcommands.CommandResetbody;
import blume_system.chat.usercommands.CommandRanks;
import blume_system.chat.usercommands.CommandStats;
import blume_system.mining.Mining;
import blume_system.settings.Config;

public class Main extends JavaPlugin {
	
	/*
	 * main class
	 */
	
	public static Plugin pluginInstance;
	  
    public void onEnable() {
    	System.out.print(Log.logInfo() + "blume_system now online");
    	System.out.print(Log.logInfo() + "	version: 1.0");
    	System.out.print(Log.logInfo() + "	spigot version: 1.8");
    	pluginInstance = this; //identify plugin instance (use "JavaPlugin" on other classes)
    	
    	getPluginInstance().saveDefaultConfig(); //create config file to save user data
    	
    	DisconnectMessages.kickOnReload(); //kick on reload
    	
    	//listeners
    	getServer().getPluginManager().registerEvents(new Mining(), this);
    	getServer().getPluginManager().registerEvents(new WorldProtection(), this);
    	getServer().getPluginManager().registerEvents(new JoinLeave(), this);
    	getServer().getPluginManager().registerEvents(new PlayerWrite(), this);
    	
    	//run classes
    	new Updater();
    	
    	//set gamerules
    	setGamerules();
    	
    	//set commands
    	setCommands();
    }
   
    public void onDisable() {
    	System.out.print(Log.logInfo() + "blume_system now offline");
    }
    
    public static Plugin getPluginInstance() { //get "JavaPlugin" instance on other classes with (Main.getPluginInstance();)
    	return pluginInstance;
    }
    
    public void setGamerules() {
    	System.out.print(Log.logInfo() + "world gamerules set");
    	
    	getPluginInstance().getServer().getWorld(Config.getWorldName()).setGameRuleValue("keepInventory", "true");
    }
    
    public void setCommands() {
    	//user commands
    	this.getCommand("stats").setExecutor(new CommandStats());
    	this.getCommand("ranks").setExecutor(new CommandRanks());
    	
    	//operator commands
    	this.getCommand("rankuptest").setExecutor(new CommandRankuptest());
    	this.getCommand("makemepro").setExecutor(new CommandMakemepro());
    	this.getCommand("resetbody").setExecutor(new CommandResetbody());
    }
}
