package de.ToxicGaming.Cloud.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import com.connorlinfoot.titleapi.TitleAPI;

import de.ToxicGaming.Cloud.Cloud;

public class Tablist {
	public static Scoreboard scoreboard;
	
	public void registerTeams() {
		scoreboard.registerNewTeam("000Admin");
		scoreboard.registerNewTeam("001Entwickler");
		scoreboard.registerNewTeam("002SrMod");
		scoreboard.registerNewTeam("003Mod");
		scoreboard.registerNewTeam("004Sup");
		scoreboard.registerNewTeam("005H-Builder");
		scoreboard.registerNewTeam("006Builder");
		scoreboard.registerNewTeam("007Designer");
		scoreboard.registerNewTeam("008VIP");
		scoreboard.registerNewTeam("009Premium");
		scoreboard.registerNewTeam("010Spieler");
		scoreboard.getTeam("000Admin").setPrefix(Rank.ADMINISTRATOR.getRankColor()+Rank.ADMINISTRATOR.getShortName()+" §8| §4");
	    scoreboard.getTeam("001Entwickler").setPrefix("§3Dev §8| §3");
	    scoreboard.getTeam("002SrModerator").setPrefix("§cSrMod §8| §c");
	    scoreboard.getTeam("003Moderator").setPrefix("§cMod §8| §c");
	    scoreboard.getTeam("004Supporter").setPrefix("§9Sup §8| §9");
	    scoreboard.getTeam("005Head-Builder").setPrefix("§fHead-B. §8| §f");
	    scoreboard.getTeam("006Builder").setPrefix("§eBuilder §8| §e");
	    scoreboard.getTeam("007Designer").setPrefix("§8Design §8| §8");
	    scoreboard.getTeam("008VIP").setPrefix("§5VIP §8| §5");
	    scoreboard.getTeam("009Premium").setPrefix("§6");
	    scoreboard.getTeam("010Spieler").setPrefix("§a");
	}
	public void setTab(Player player) {
		TitleAPI.sendTabTitle(player, "§8» §6§lMakrali-Netzwerk §8«", "§eTeamspeak³: §7Makrali.net\n §eForum: §7Makrali.net \n §eServer: §7" + Bukkit.getServer().getMotd());
	}
	@SuppressWarnings("deprecation")
	public void setPrefix(Player player) {
		Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
	    String team = "";
	    if (rank.equalsLevel(Rank.ADMINISTRATOR)) {
	      team = "000Admin";
	    } else if (rank.equalsLevel(Rank.ENTWICKLER)) {
	      team = "001Entwickler";
	    } else if (rank.equalsLevel(Rank.SRMODERATOR)) {
	      team = "002SrModerator";
	    } else if (rank.equalsLevel(Rank.MODERATOR)) {
	      team = "003Moderator";
	    } else if (rank.equalsLevel(Rank.SUPPORTER)) {
	      team = "004Supporter";
	    } else if (rank.equalsLevel(Rank.HEADBUILDER)) {
	      team = "005Head-Builder";
	    } else if (rank.equalsLevel(Rank.BUILDER)) {
	      team = "006Builder";
	    } else if (rank.equalsLevel(Rank.BUILDER)) {
	      team = "007Designer";
	    } else if (rank.equalsLevel(Rank.VIP)) {
	      team = "008VIP";
	    } else if (rank.equalsLevel(Rank.PREMIUM)) {
	      team = "009Premium";
	    } else if (rank.equalsLevel(Rank.SPIELER)){
	      team = "010Spieler";
	    }
	    scoreboard.getTeam(team).addPlayer(player);
	    for (Player all : Bukkit.getOnlinePlayers()) {
	      all.setScoreboard(scoreboard);
	    }
	 }
	public void reloadTab() {
		for(Player players : Bukkit.getOnlinePlayers()) {
			setPrefix(players);
		}
	}
}
