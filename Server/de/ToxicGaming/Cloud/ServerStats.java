package de.ToxicGaming.Cloud;

import org.bukkit.Bukkit;

import lombok.Getter;

public class ServerStats {
	@Getter
	private static int currentPlayers = Bukkit.getServer().getOnlinePlayers().size();
	@Getter
	private static int maxPlayers = Bukkit.getServer().getMaxPlayers();
	@Getter
	private static String worldNames = Bukkit.getServer().getWorlds().toString();
	@Getter
	private static long maxRam = Runtime.getRuntime().totalMemory();
	@Getter
	private static String startTime = Cloud.getTimeManager().getEndeAnzeige(Cloud.getStartTime());
}
