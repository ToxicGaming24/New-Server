package de.ToxicGaming.Cloud.Utils;

import java.util.UUID;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import lombok.Getter;

public class PlayerInfo {
	@Getter
	private static int port;
	@Getter
	private static UUID uuid;
	@Getter
	private static String name;
	@Getter
	Server server;
	@Getter
	private static String ip;
	
	public void getPlayerInfo(Player player, Player sender) {
		port = player.getAddress().getPort();
		uuid = player.getUniqueId();
		name = player.getName();
		server = player.getServer();
		ip = player.getAddress().getAddress().getHostAddress();
		sender.sendMessage(Cloud.getPrefix()+"§2Spielerinfo von: "+name+":\n§6IP: "+ip+"\n§6UniqueID: "+uuid+"\n§6IP: "+ip+":"+port);
	}
}
