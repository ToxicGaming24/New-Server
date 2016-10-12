package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class BanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		Player target = ( Player ) Bukkit.getPlayer(args[1]);
		Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
		if(rank.isHigherEqualsLevel(Rank.MODERATOR)) {
			switch (args[0]) {
			case "ban":
				/*
				 * Ban <SpielerName> <Grund>
				 */
				if(args.length >= 2) {
					Cloud.getDbPool().insertToBan(target.getUniqueId(), target.getName(), args[2]);
					Cloud.getBanned().add(target);
				}else {
					return true;
				}
			case "unban":
				/*
				 * Unban <SpielerName>
				 */
				if(args.length == 1) {
					Cloud.getDbPool().removeFromBan(target.getUniqueId(), target.getName());
				}else {
					return true;
				}
			}
		}else {
			player.sendMessage(Cloud.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
		}
		return false;
	}
}
