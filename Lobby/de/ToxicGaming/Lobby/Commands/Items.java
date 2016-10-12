package de.ToxicGaming.Lobby.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;
import de.ToxicGaming.Lobby.Lobby;
import de.ToxicGaming.Lobby.LobbyClient;

public class Items implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		switch (args.length) {
		case 0:
			LobbyClient.setLobbyItems(player);
		case 1:
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			Player target = Bukkit.getPlayer(args[0]);
			Rank targetRank = Cloud.getDbPool().getPlayerRank(target.getUniqueId());
			if(rank.isHigherEqualsLevel(Rank.SUPPORTER)) {
			LobbyClient.setLobbyItems(target);
			player.sendMessage("§2Du hast dem Spieler: "+targetRank.getRankColor()+target.getName()+"§2 die Lobbyitems gegeben.");
				}else {
					player.sendMessage(Lobby.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
					return true;
				}
			}
		return false;
	}
}
