package de.ToxicGaming.Cloud.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.CustomTimingsHandler;
import de.ToxicGaming.Cloud.Utils.Rank;


public class PlayerCommandPreprocess implements Listener {
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage();
		Player player = ( Player ) event.getPlayer();
		Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
		if(message.equalsIgnoreCase("/pl") || message.equalsIgnoreCase("/plugins") || message.equalsIgnoreCase("/?") || message.equalsIgnoreCase("/help")) {
			if(rank.isHigherEqualsLevel(Rank.ENTWICKLER)) {
				player.sendMessage("§2Installierte Plugins: §6"+Bukkit.getPluginManager().getPlugins());
			}else {
				event.setCancelled(false);
				Bukkit.getConsoleSender().sendMessage("The player "+event.getPlayer().getName()+" has tried to see the Plugins!");
			}
		}
		if(message.equalsIgnoreCase("/rl") || message.equalsIgnoreCase("/reload")) {
			if(rank.isHigherEqualsLevel(Rank.ENTWICKLER)) {
				event.setCancelled(true);
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.sendMessage(Cloud.getPrefix()+"Der Server wird in §25 §6Sekunden neu geladen!");
				}
				new Thread(() -> {
				Bukkit.getScheduler().runTaskLater(Cloud.getInstance(), (() -> {
					CustomTimingsHandler.reload();
				}),100L);
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.sendMessage(Cloud.getPrefix()+"§2Reload abgeschlossen!");
				}
				}).start();
			}else {
				event.setCancelled(true);
			}
		}
		if(message.equalsIgnoreCase("/server")) {
			if(!rank.isHigherEqualsLevel(Rank.SRMODERATOR)) {
				event.setCancelled(true);
				player.sendMessage(Cloud.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
			}
		}
	}
}
