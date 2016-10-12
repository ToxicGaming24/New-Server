package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Kick extends Command {

	public Kick() {
		super("kick", "cloud.kick");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = ( ProxiedPlayer ) sender;
		ProxiedPlayer target = ( ProxiedPlayer ) ProxyServer.getInstance().getPlayer(args[1]);
		if(player.hasPermission(getPermission())) {
			if(args.length >= 2) {
				target.disconnect("§6Makrali.net\n§cDu wurdest vom Netzwerk gekickt!\n§6Grund: §8"+args);
			}else {
				player.sendMessage(BungeeProxy.getPrefix()+"§6Benutze §8/kick <SpielerName> <Grund>");
			}
		}else {
			player.sendMessage(BungeeProxy.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
			return;
		}
	}
}
