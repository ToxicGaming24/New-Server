package de.ToxicGaming.Cloud.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.ToxicGaming.Cloud.Cloud;

public class PlayerQuitListener implements Listener {
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		Cloud.getTablist().reloadTab();
	}
}
