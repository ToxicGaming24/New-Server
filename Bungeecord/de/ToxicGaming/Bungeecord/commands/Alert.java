package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Alert extends Command{
	public Alert() {
		super("alert", "server.alert");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = ( ProxiedPlayer ) sender;
		if(player.hasPermission(this.getPermission())) {
			if(args.length >= 1) {
				for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
					String[] message = args;
					if(message.toString().contains("&")) {
						message.toString().replaceAll("&","§");
					players.sendMessage(BungeeProxy.getPrefix()+"§6§lRundruf: §r"+message);
					}else {
						players.sendMessage(BungeeProxy.getPrefix()+"§6§lRundruf: §r§2"+message);
					}
				}
			}
		}
	}
}
