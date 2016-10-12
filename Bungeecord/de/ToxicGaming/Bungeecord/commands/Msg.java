package de.ToxicGaming.Bungeecord.commands;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg extends Command {
	public Msg() {
		super("msg");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length <=2) {
			sender.sendMessage(Cloud.getPrefix()+"§6Benutze §8/msg <SpielerName> <Nachricht>");
			return;
		}else {
			ProxiedPlayer targetPlayer = ( ProxiedPlayer ) ProxyServer.getInstance().getPlayer(args[1]);
			ProxiedPlayer player = ( ProxiedPlayer ) sender;
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			targetPlayer.sendMessage(rank.getRankColor()+rank.getRankName()+player.getName()+" §8»» Dir »» §7"+args);
		}
	}
}
