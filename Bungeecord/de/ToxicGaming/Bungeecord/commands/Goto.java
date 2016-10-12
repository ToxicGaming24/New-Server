package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Goto extends Command {
	public Goto() {
		super("goto", "cloud.goto", "");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] strings) {
		ProxiedPlayer player = ( ProxiedPlayer ) sender;
		if(strings.length == 1) {
			if(player.hasPermission(getPermission())) {
				ProxiedPlayer target = ( ProxyServer.getInstance().getPlayer(strings[1]));
				player.connect((ServerInfo) target.getServer());
			}else {
				player.sendMessage(BungeeProxy.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
			}
		}else {
			player.sendMessage("§6Benutze §8/goto <Spielername>§6!");
		}
	}
}
