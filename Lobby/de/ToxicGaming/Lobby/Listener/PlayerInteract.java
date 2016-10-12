package de.ToxicGaming.Lobby.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.ToxicGaming.Lobby.LobbyClient;

public class PlayerInteract implements Listener{
	 @EventHandler
	 public void onInteract(PlayerInteractEvent event) {
		 ItemStack current = event.getPlayer().getItemInHand();
		 Player player = ( Player ) event.getPlayer();
		 if(player.isSneaking()) {
			 if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				 Block block = event.getClickedBlock();
			 player.sendMessage("§6Location des Blockes: §8X:"+block.getX()+" Y:"+block.getY()+" Z:"+block.getZ());
			 }
		 }
		 if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			 if(current.getType() == Material.COMPASS) {
				 LobbyClient.createTeleporter(player);
				 
			 }
		 }
	 }
}