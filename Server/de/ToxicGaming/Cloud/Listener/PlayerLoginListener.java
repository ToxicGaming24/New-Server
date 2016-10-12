package de.ToxicGaming.Cloud.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.ToxicGaming.Cloud.Cloud;

public class PlayerLoginListener implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		if(Cloud.getBanned().contains(event.getPlayer())) {
//			Connection connection = MySQL.getConnection();
//			try {
//				PreparedStatement statement = connection.prepareStatement("SELECT * FROM ban WHERE name =?, grund = ?");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			String reason = null;
			event.disallow(Result.KICK_OTHER, "§6Makrali Netzwerk\n"
					+ "§cDu bist vom Netzwerk gebannt!\n"
					+ "§6Grund: §8 "+reason);
		}
	}
}
