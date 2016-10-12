package de.ToxicGaming.Lobby.Listener;


import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpPads implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(event.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE) {
			Vector vector = event.getPlayer().getLocation().getDirection().multiply(10.0D).setY(1D);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENDERDRAGON_WINGS, 5, 5);
			event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.ENDER_SIGNAL, 5);
			event.getPlayer().setVelocity(vector);
		}
	}
}
