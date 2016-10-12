package de.ToxicGaming.Lobby.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.ToxicGaming.Lobby.Lobby;
import de.ToxicGaming.Lobby.LobbyClient;

public class Build implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			if(sender.hasPermission("lobby.build")) {
				if(!LobbyClient.getBuildPlayers().contains(Bukkit.getPlayer(sender.getName()))) {
				LobbyClient.getBuildPlayers().add(Bukkit.getPlayer(sender.getName()));
				sender.sendMessage(Lobby.getPrefix()+"§6Du kannst nun in der Lobby bauen!");
				}else if(LobbyClient.getBuildPlayers().contains(Bukkit.getPlayer(sender.getName()))) {
					LobbyClient.getBuildPlayers().remove(Bukkit.getPlayer(sender.getName()));
					sender.sendMessage(Lobby.getPrefix()+"§6Du kannst nun nicht mehr in der Lobby bauen!");
				}
			}else {
				return true;
			}
		}else {
			return true;
		}
		return false;
	}
}
