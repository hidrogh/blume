package blume_system.chat;

import org.bukkit.entity.Player;

public class ChatMessages {
	public static void notFound(Player p) {
		p.sendMessage(ChatTags.logBlume() + "Command not found. Please enter '/help' for more information.");
	}
}
