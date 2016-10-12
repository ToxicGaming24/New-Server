package de.ToxicGaming.Lobby.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ToxicGaming.Lobby.LobbyClient;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = ( Player ) event.getPlayer();
		player.teleport(LobbyClient.getSpawnLocation());
		LobbyClient.setLobbyItems(player);
		event.setJoinMessage(null);
	}
}
