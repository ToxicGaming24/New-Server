package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class ClearchatCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender; 
		if(args.length == 0) {
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			if(rank.isHigherEqualsLevel(Rank.SUPPORTER)) {
				for(int i = 0; i <= 200; i++) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.sendMessage(" ");
					}
				}
			}else {
				player.sendMessage(Cloud.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
			}
		}else {
			player.sendMessage(Cloud.getPrefix()+"§6Benutze §8/cc");
		}
		return false;
	}
}
