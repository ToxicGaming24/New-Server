package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Ping extends Command{
	public Ping() {
		super("ping");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] strings) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if(strings.length == 0) {
			player.sendMessage(BungeeProxy.getPrefix()+"§6Dein Ping beträgt §8"+player.getPing()+" §6MS!");
			ProxyServer.getInstance().getConsole().sendMessage("Player "+player.getName()+" checked his ping ... result: "+player.getPing());
		}
	}
}
