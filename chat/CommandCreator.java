package blume_system.chat;

import org.bukkit.command.CommandExecutor;

import blume_system.settings.Config;

public class CommandCreator{
	
	/*
	 * commands, description
	 * 
	 * - add in this class: command, class, description
	 * - create class and edit command
	 * - add command to "plugin.yml"
	 */
	
	public static int commandPos = 0;
	
	public static String userCommands[] = Config.userCommands;
	public static CommandExecutor userCommandClasses[] = Config.userCommandClasses;
	public static String userCommandDesk[] = Config.userCommandDesk;
	
	
	public static void setCommandPos(int pos) {
		commandPos = pos;
	}
	
    public static String getCommands() {
    	return userCommands[commandPos];
    }
    public static CommandExecutor getCommandClasses() {
    	return userCommandClasses[commandPos];
    }
    public static String getCommandDesc() {
    	return userCommandDesk[commandPos];
    }
}
