package blume_system.config;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import blume_system.general.Main;
import blume_system.settings.Config;
import net.md_5.bungee.api.ChatColor;

public class PlayerScoreboard {
	
	static String oldScoreRank = "";
	static String oldScoreMoney = "";
	static String oldScoreLevel = "";
	static String oldScoreNextLevel = "";
	
	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	static Scoreboard scoreboard = manager.getNewScoreboard();
	static Objective o = scoreboard.registerNewObjective("scoreboard", "");
	
	public static void create(Player p) { //create scoreboard
		o.setDisplaySlot(DisplaySlot.SIDEBAR); //positioning
		o.setDisplayName(ChatColor.YELLOW + "§lBLUME"); //title
		
		refresh(p); //enter values
		
		p.getPlayer().setScoreboard(scoreboard); //create
	}
	
	public static void refresh(Player p) { //refresh scoreboard
		String currency = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".currency");
		String level_global = Main.getPluginInstance().getConfig().getString("players.uuid-" + p.getUniqueId().toString() + ".level_global");
		String playerRank = Config.getGlobalRank(p);
		
		String scoreRank = Config.sbRank + playerRank;
		String scoreMoney = Config.sbMoney + currency + "$";
		String scoreLevel = Config.sbLevel + level_global;
		String scoreNextLevel;
		if (UntilNextLvl.getUntilNextLvl(p) == 987654321) { //if player got max level
			scoreNextLevel = Config.sbNextMaxLevel;
		} else {
			scoreNextLevel = Config.sbNextLevel + Integer.toString(UntilNextLvl.getUntilNextLvl(p));
		}
		
		
		//reset and delete old scores
		if (oldScoreRank != scoreRank && oldScoreRank != "") {
			scoreboard.resetScores(oldScoreRank);
		}
		if (oldScoreMoney != scoreMoney && oldScoreMoney != "") {
			scoreboard.resetScores(oldScoreMoney);
		}
		if (oldScoreLevel != scoreLevel && oldScoreLevel != "") {
			scoreboard.resetScores(oldScoreLevel);
		}
		if (oldScoreNextLevel != scoreNextLevel && oldScoreNextLevel != "") {
			scoreboard.resetScores(oldScoreNextLevel);
		}
		
		//values
		Score rank = o.getScore(scoreRank);
		rank.setScore(5);
		
		Score clear = o.getScore("");
		clear.setScore(4);
		
		Score money = o.getScore(scoreMoney);
		money.setScore(3);
		
		Score level = o.getScore(scoreLevel);
		level.setScore(2);
		
		if (!p.isOp()) { //op does not need next level
			Score clear2 = o.getScore("");
			clear2.setScore(1);
			
			Score nextLevel = o.getScore(scoreNextLevel);
			nextLevel.setScore(0);
		}
		
		//save old scores to reset on next run
		oldScoreRank = scoreRank;
		oldScoreMoney = scoreMoney;
		oldScoreLevel = scoreLevel;
		oldScoreNextLevel = scoreNextLevel;
	}
}
