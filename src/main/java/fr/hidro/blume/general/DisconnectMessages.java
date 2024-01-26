package fr.hidro.blume.general;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DisconnectMessages {
    public static void kickOnReload() {
        //for every player in the server
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            //if the player is OP then return
            if (p.isOp()) {
                return;
            }
            final TextComponent rebootKickMessage = Component.text("The server is rebooting, you will be able to join shortly.",NamedTextColor.WHITE);
            //kick the player with message
            p.kick(rebootKickMessage);
        }
    }
    //kick if op
    public static void kickOnOp() {
        for (Player p:Bukkit.getServer().getOnlinePlayers()) {
            if (!p.isOp()) {
                return;
            }
            final TextComponent rebootKickMessageOp = Component.text("The server is rebooting, check console.",NamedTextColor.WHITE);
            p.kick(rebootKickMessageOp);
        }
    }
}
