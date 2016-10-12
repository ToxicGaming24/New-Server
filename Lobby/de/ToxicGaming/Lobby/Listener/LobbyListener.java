package de.ToxicGaming.Lobby.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.ToxicGaming.Lobby.LobbyClient;

public class LobbyListener implements Listener {
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onPickUpItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {
		LobbyClient.setLobbyItems(event.getPlayer());
		event.setCancelled(true);
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = ( Player ) event.getPlayer();
		if(!LobbyClient.getBuildPlayers().contains(player)) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = ( Player ) event.getPlayer();
		if(!LobbyClient.getBuildPlayers().contains(player)) {
			event.setCancelled(true);
		}
	}
}
