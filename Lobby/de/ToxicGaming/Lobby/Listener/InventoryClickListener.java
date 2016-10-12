package de.ToxicGaming.Lobby.Listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import de.ToxicGaming.Lobby.Lobby;
import de.ToxicGaming.Lobby.LobbyClient;

public class InventoryClickListener implements Listener {
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = ( Player ) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		if(event.getAction() == InventoryAction.PICKUP_ALL || event.getAction() == InventoryAction.PICKUP_HALF || event.getAction() == InventoryAction.PICKUP_ONE || event.getAction() == InventoryAction.PICKUP_SOME || event.getAction() == InventoryAction.SWAP_WITH_CURSOR || event.getAction() == InventoryAction.HOTBAR_SWAP) {
			if(!LobbyClient.getBuildPlayers().contains(player)) {
				event.setCancelled(true);
			}
		}
		if(event.getClickedInventory() != null || event.getClickedInventory().getTitle().equalsIgnoreCase("§6Navigator")) {
			if(clicked.getType() == Material.FIREWORK_CHARGE) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
				player.teleport(LobbyClient.getSpawnLocation());
			}else if(clicked.getType() == Material.EMERALD_BLOCK) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
				player.sendMessage(Lobby.getPrefix()+"§6Unser Onlineshop: §8---");
			}else if(clicked.getType() == Material.FEATHER) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
				player.teleport(LobbyClient.getSkyPvPLocation());
			}else if(clicked.getType() == Material.BED) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
				player.teleport(LobbyClient.getBedwarsLocation());
			}else if(clicked.getType() == Material.SPONGE) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
				player.teleport(LobbyClient.getLuckySGLocation());
			}else if(clicked.getType() == Material.STAINED_GLASS_PANE) {
				playSound(player, Sound.CLICK);
				player.closeInventory();
			}
		}
	}
	public void playSound(Player player,Sound sound) {
		player.playSound(player.getLocation(), sound, 5, 5);
	}
	@EventHandler
	public void onMoveItem(InventoryPickupItemEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onDrag(InventoryDragEvent event) {
		event.setCancelled(true);
	}
}
