package de.ToxicGaming.Cloud.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class PlayerChatListener implements Listener{
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Rank rank = Cloud.getDbPool().getPlayerRank(event.getPlayer().getUniqueId());
		/*
		 * Rankcolor+Rankname | name > message
		 */
		event.setFormat(rank.getRankColor()+rank.getRankName()+" §8| "+rank.getRankColor()+event.getPlayer().getName()+" §8»» §7"+message);
	}
}
