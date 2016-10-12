package de.ToxicGaming.Bungeecord.Listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerKick implements Listener{
	@EventHandler
	public void onKick(ServerKickEvent event) {
		BaseComponent[] reason = event.getKickReasonComponent();
		if(reason.toString().toLowerCase().contains("lobby") || reason.toString().toLowerCase().equalsIgnoreCase("lobby")) {
			event.setCancelServer(ProxyServer.getInstance().getServerInfo("lobby"));
			event.setCancelled(true);
		}
	}
}
