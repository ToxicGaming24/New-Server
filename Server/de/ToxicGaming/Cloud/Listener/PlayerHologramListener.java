package de.ToxicGaming.Cloud.Listener;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import de.ToxicGaming.Cloud.Utils.Hologram.HologramManager;

public class PlayerHologramListener implements Listener {
    @EventHandler
    public void onPlayerQuit( PlayerQuitEvent event ) {
        Player player = event.getPlayer();
        HologramManager.handlePlayerQuit( player );
    }
    @EventHandler
    public void onPlayerMove( PlayerMoveEvent event ) {
        if ( event.getFrom().getWorld() == event.getTo().getWorld() &&
                event.getFrom().getBlockX() == event.getTo().getBlockX() &&
                event.getFrom().getBlockY() == event.getTo().getBlockY() &&
                event.getFrom().getBlockZ() == event.getTo().getBlockZ())
            return;

        Player player = event.getPlayer();
        if ( player.getHealth() <= 0.0D ) return;

        Chunk oldChunk = event.getFrom().getChunk();
        Chunk newChunk = event.getTo().getChunk( );

        if ( oldChunk.getWorld() != newChunk.getWorld() || oldChunk.getX() != newChunk.getX() || oldChunk.getZ() != newChunk.getZ() ) {
            HologramManager.updatePlayerView( player );
        }

    }
    @EventHandler
    public void onPlayerTeleport( PlayerTeleportEvent event ) {
        HologramManager.updatePlayerView( event.getPlayer() );
    }
}
