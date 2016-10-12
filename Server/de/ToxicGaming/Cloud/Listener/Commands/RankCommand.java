package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class RankCommand implements CommandExecutor {
	/*
	 * Syntax:
	 * 		 /Rang <SpielerName> <RangName>
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		if(args.length == 2) {
			Player target = ( Player ) Bukkit.getPlayer(args[1]);
			Rank getRank = Rank.fromStringExact(args[2]);
			player.sendMessage(Cloud.getPrefix()+"§2Der Rang: "+getRank.getRankColor()+getRank.getRankName()+" §2Wurde dem Spieler: §6"+getRank.getRankColor()+target.getName()+" §2Zugewiesen!");
			target.sendMessage(Cloud.getPrefix()+"§2Dein Rang wurde auf "+getRank.getRankColor()+getRank.getRankName()+" §2Geändert!");
			Cloud.getDbPool().updatePlayer(target.getUniqueId(), target.getName(), getRank.getRankName(), target.getAddress().getAddress().getHostAddress());
			Bukkit.getConsoleSender().sendMessage("Dem Spieler: "+target.getName()+" wurde der Rang: "+getRank.getRankName()+" zugewiesen!");
			return true;
		}else {
			player.sendMessage(Cloud.getPrefix()+"§6Benutze /rang <SpielerName> <RangName>\n§2Verfügbare Ränge: §6"+Rank.getRanks());
		}
		return false;
	}
}
