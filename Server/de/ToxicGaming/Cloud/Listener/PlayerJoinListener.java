package de.ToxicGaming.Cloud.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = ( Player ) event.getPlayer();
		String name = player.getName();
		Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
		event.setJoinMessage(null);
		if(player.getServer().getMotd().toLowerCase().contains("build-")) {
			if(rank.equalsLevel(Rank.BUILDER) || rank.equalsLevel(Rank.HEADBUILDER) || rank.equalsLevel(Rank.ADMINISTRATOR) || rank.equalsLevel(Rank.ENTWICKLER)) {
				player.setOp(true);
			}
		}
		Cloud.getTablist().setPrefix(event.getPlayer());
		Cloud.getTablist().reloadTab();
		Cloud.getTablist().setTab(event.getPlayer());
		if(!player.hasPlayedBefore()) {
			String rankName = Rank.SPIELER.getRankName();
			Cloud.getDbPool().storePlayer(player.getUniqueId(), name, rankName, player.getAddress().getAddress().getHostAddress());
		}
	}
}