package de.ToxicGaming.Cloud.Listener.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Utils.Rank;

public class StopCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = ( Player ) sender;
		if(args.length == 0) {
			Rank rank = Cloud.getDbPool().getPlayerRank(player.getUniqueId());
			if(rank.equalsLevel(Rank.ADMINISTRATOR)) {
				Command.broadcastCommandMessage(sender, "§cDer Server wird gestopppt!");
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.kickPlayer("§8«§6§lMakrali Netzwerk§r§8»\n§cDer Server wurde gestoppt!");
					Bukkit.getConsoleSender().sendMessage("The player "+player.getName()+" has stopped the Server!");
					new Thread(() -> {
						for(World worlds : Bukkit.getWorlds()) {
							worlds.save();
							worlds.getEntities().clear();
							for(Chunk chunks : worlds.getLoadedChunks()) {
								chunks.unload();
							}
						}
					}).start();
					Bukkit.shutdown();
					return true;
				}
			}else {
				player.sendMessage(Cloud.getPrefix()+"§cDu hast keine Berechtigung um diesen Befehl ausführen zu können!");
				return true;
			}
		}else {
			player.sendMessage(Cloud.getPrefix()+"§6Benutze §8/stop§6 !");
			return true;
		}
		return false;
	}
}
