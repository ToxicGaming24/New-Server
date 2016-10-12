package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Bungeecord.BungeeProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TeamChat extends Command{

	public TeamChat() {
		super("tc", "server.tc", "teamchat");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = ( ProxiedPlayer ) sender;
		if(args.length >=1) {
			if(player.hasPermission(getPermission())) {
			String prefix = PermissionsEx.getUser(player.getName()).getPrefix();
			for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
				all.sendMessage(prefix+player.getName()+" §8| §7"+args);
			}
			}else{
				player.sendMessage(BungeeProxy.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
				return;
			}
		}else {
			player.sendMessage("§6Benutze §8/tc <Nachricht>");
			return;
		}
	}
}
