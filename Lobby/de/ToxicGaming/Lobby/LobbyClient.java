package de.ToxicGaming.Lobby;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.ToxicGaming.Lobby.Utils.ItemStackBuilder;
import lombok.Getter;

public class LobbyClient {
	@Getter
	private static List<Player> buildPlayers = new ArrayList<>();
	
	//Locations
	@Getter
	private static Location spawnLocation = new Location(Bukkit.getWorld("world"), 139.500, 133, 635.500,-90,0);
	@Getter
	private static Location bedwarsLocation = new Location(Bukkit.getWorld("world"), 139.500, 132, 580.500);
	@Getter
	private static Location skyPvPLocation = new Location(Bukkit.getWorld("world"), 194.500, 132, 635.500);
	@Getter
	private static Location LuckySGLocation = new Location(Bukkit.getWorld("world"), 85.500, 132, 635.500);
	
	//Items
	public static void setLobbyItems(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		List<String> navilore = new ArrayList<>();
		navilore.add("§8»-----------------------------------«");
		navilore.add("§2Teleportiert dich zu einem ausgewähltem Spielmodi.");
		navilore.add("§8»-----------------------------------«");
		ItemStack navigator = ItemStackBuilder.createItemStack(Material.COMPASS, 0, "§6Navigator §8»§aRechtsklick§8«", 1, navilore);
		List<String> shoplore = new ArrayList<>();
		shoplore.add("§8»-----------------------------------«");
		shoplore.add("§2Oeffnet den Coinshop");
		shoplore.add("§6Du bekommst Coins durch das Spielen von Spielmodis");
		shoplore.add("§8»-----------------------------------«");
		ItemStack shop = ItemStackBuilder.createItemStack(Material.EMERALD, 0, "§6CoinShop §8»§aRechtsklick§8«", 1, shoplore);
		player.getInventory().setItem(0, navigator);
		player.getInventory().setItem(2, shop);
	}
	public static void createTeleporter(Player player) {
		Inventory navigator = Bukkit.createInventory(null, 27, "§6Navigator");
		ItemStack normalGlass = ItemStackBuilder.createItemStack(Material.STAINED_GLASS_PANE, 0, " ");
		ItemStack blackGlass = ItemStackBuilder.createItemStack(Material.STAINED_GLASS_PANE, 15, " ");
		navigator.setItem(0, normalGlass);
		navigator.setItem(1, normalGlass);
		navigator.setItem(2, normalGlass);
		navigator.setItem(3, blackGlass);
		navigator.setItem(4, ItemStackBuilder.createItemStack(Material.FIREWORK_CHARGE, 0, "§6Spawn"));
		navigator.setItem(5, blackGlass);
		navigator.setItem(6, normalGlass);
		navigator.setItem(7, normalGlass);
		navigator.setItem(8, normalGlass);
		
		navigator.setItem(9, normalGlass);
		navigator.setItem(10, blackGlass);
		navigator.setItem(11, ItemStackBuilder.createItemStack(Material.SPONGE, 0, "§6Lucky-SurvivalGames"));
		navigator.setItem(12, blackGlass);
		navigator.setItem(13, ItemStackBuilder.createItemStack(Material.BED, 0, "§6Bedwars"));
		navigator.setItem(14, blackGlass);
		navigator.setItem(15, ItemStackBuilder.createItemStack(Material.FEATHER, 0, "§6SkyPvP"));
		navigator.setItem(16, blackGlass);
		navigator.setItem(17, normalGlass);
		
		navigator.setItem(18, normalGlass);
		navigator.setItem(19, normalGlass);
		navigator.setItem(20, normalGlass);
		navigator.setItem(21, blackGlass);
		navigator.setItem(22, ItemStackBuilder.createItemStack(Material.EMERALD_BLOCK, 0, "§6OnlineShop"));
		navigator.setItem(23, blackGlass);
		navigator.setItem(24, normalGlass);
		navigator.setItem(25, normalGlass);
		navigator.setItem(26, normalGlass);
		player.openInventory(navigator);
	}
}
