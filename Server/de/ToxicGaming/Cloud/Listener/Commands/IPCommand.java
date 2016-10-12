package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class IPCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		if(args.length == 1) {
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			if(rank.equalsLevel(Rank.ADMINISTRATOR)) {
				Player target = Bukkit.getPlayer(args[1]);
				player.sendMessage(Cloud.getPrefix()+"§6IP-Adresse von §2"+target.getName()+"§6: §8"+target.getAddress().getAddress().getHostAddress());
			}else {
			return true;	
			}
		}else {
			player.sendMessage(Cloud.getPrefix()+"§6Benutze §8/ip <Spieler>");
		}
		return false;
	}
}
