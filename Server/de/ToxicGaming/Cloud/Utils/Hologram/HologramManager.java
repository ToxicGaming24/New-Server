package de.ToxicGaming.Cloud.Utils.Hologram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.ToxicGaming.Cloud.Cloud;
import de.ToxicGaming.Cloud.Listener.PlayerHologramListener;
import lombok.Getter;

public class HologramManager {
	@Getter
    private static HashMap<Player,List<Hologram>> playerHolograms = new HashMap<>(  );
    public static void addPlayerToHologram( Player player, Hologram hologram ) {
        if( !playerHolograms.containsKey( player ) ) {
            playerHolograms.put( player, new ArrayList<>(  ) );
        }
        List<Hologram> holograms = playerHolograms.get( player );
        holograms.add( hologram );
        playerHolograms.put( player, holograms );
    }
    public static void handlePlayerQuit( Player player ) {
        if( playerHolograms.containsKey( player ) ) {
            for( Hologram hologram : playerHolograms.get( player ) ) {
                hologram.getLoadedPlayers().remove( player );
            }
            playerHolograms.remove( player );
        }
    }
    public static void removePlayerHologram( Player player, Hologram hologram ) {
        if( !playerHolograms.containsKey( player ) ) return;
        List<Hologram> holograms = playerHolograms.get( player );
        holograms.remove( hologram );
        playerHolograms.put( player, holograms );
    }
    public static void updatePlayerView( Player player ) {
        if( !playerHolograms.containsKey( player ) ) return;
        for( Hologram hologram : playerHolograms.get( player ) ) {
            hologram.checkSending( player );
        }
    }
    public static void enableListeners() {
        Bukkit.getServer( ).getPluginManager().registerEvents( new PlayerHologramListener(), Cloud.getInstance() );
    }
}
