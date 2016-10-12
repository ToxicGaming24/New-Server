package de.ToxicGaming.Bungeecord.Listener;

import java.util.UUID;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoin implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(LoginEvent event) {
		String playerName = event.getConnection().getName();
		UUID uniqueID = event.getConnection().getUniqueId();
		String ipAddress = event.getConnection().getAddress().getAddress().getHostAddress();
		ProxiedPlayer player = BungeeCord.getInstance().getPlayer(playerName);
		ProxyServer.getInstance().getConsole().sendMessage("Player "+playerName+" connected with ip "+ipAddress+"\n"
		+"UniqueID from "+playerName+": "+uniqueID);
//		BungeeProxy.getTimeOutmanager().checkTimeout(playerName);
		if(BungeeProxy.isWartung() || !player.hasPermission("server.join.wartung")) {
			event.setCancelled(true);
			event.setCancelReason("§6Makrali.net\n§cEs konnten keine Daten synchronisiert werden!");
			return;
		}
		if(ProxyServer.getInstance().getPlayers().size() >= ProxyServer.getInstance().getConfig().getPlayerLimit()) {
			event.setCancelled(true);
			event.setCancelReason("§6Makrali.net\n§cDas Netzwerk ist voll!");
			event.completeIntent(BungeeProxy.getInstance());
			return;
		}
		event.completeIntent(BungeeProxy.getInstance());
	}
}
