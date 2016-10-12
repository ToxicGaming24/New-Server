package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class Gamemode implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		// GM mode
		if(args.length == 1) {
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			if(rank.isHigherEqualsLevel(Rank.SRMODERATOR)) {
			int mode = Integer.valueOf(args[1]);
			@SuppressWarnings("deprecation")
			GameMode gamemode = GameMode.getByValue(mode);
			player.setGameMode(gamemode);
			player.sendMessage(Cloud.getPrefix()+"§2Dein Gamemode wurde auf §6"+gamemode.name()+" §2Gesetzt.");
			}else {
				player.sendMessage(Cloud.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
				return true;
			}
		}
		return true;
	}
}
