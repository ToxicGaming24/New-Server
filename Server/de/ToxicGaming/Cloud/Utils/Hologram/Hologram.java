package de.ToxicGaming.Cloud.Utils.Hologram;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;

@SuppressWarnings("rawtypes")
public class Hologram {
	@Getter
    private Location location;
    @Getter
    private List<HoloLine> lines = new ArrayList<>(  );
    @Getter
    private List<Player> loadedPlayers = new ArrayList<>(  );
    public Hologram( Location location ) {
        this.location = location;
    }
    public void addTextLine( String text ) {
        lines.add( new TextLine( text, EntityIDUtil.getRandomEntityID( ) ) );
    }
    public void addItemLine( ItemStack itemStack ) {
        lines.add( new ItemLine( EntityIDUtil.getRandomEntityID(), EntityIDUtil.getRandomEntityID(), itemStack ) );
    }
    public void createHologramForPlayer( Player player ) {
        HologramManager.addPlayerToHologram( player, this );
        checkSending( player );
    }
    public void removeHologramForPlayer( Player player ) {
        HologramManager.removePlayerHologram( player, this );
        removeFromPlayer( player );
    }
    public void checkSending( Player player ) {
        if( isInRange( player ) ) {
            sendToPlayer( player );
        } else {
            removeFromPlayer( player );
        }
    }
    private void sendToPlayer( Player player ) {
        if( isPlayerLoaded( player ) ) return;
        loadedPlayers.add( player );
        double locX = location.getX();
        double locY = location.getY( );
        double locZ = location.getZ();
        PlayerConnection playerConnection = ( ( CraftPlayer ) player ).getHandle( ).playerConnection;
        for( HoloLine holoLine : lines ) {
            Location spawnLocation = new Location( location.getWorld( ), locX, locY, locZ );
            for( Packet packet : holoLine.getSpawnPackets( spawnLocation ) ) {
                playerConnection.sendPacket( packet );
            }
            if( holoLine instanceof ItemLine ) {
                locY -= 1.0D;
            } else {
                locY -= 0.3D;
            }
        }
    }
	private void removeFromPlayer( Player player ) {
        if( !isPlayerLoaded( player ) ) return;
        for( HoloLine holoLine : lines ) {
            PlayerConnection playerConnection = ( ( CraftPlayer ) player ).getHandle( ).playerConnection;
            for( Packet packet : holoLine.getDespawnPackets( ) ) {
                playerConnection.sendPacket( packet );
            }
        }
        loadedPlayers.remove( player );
    }
    public boolean isPlayerLoaded( Player player ) {
        return loadedPlayers.contains( player );
    }
    public boolean isInRange( Player player ) {
        return location.getWorld() == player.getLocation().getWorld() && ( location.distance( player.getLocation() ) <= 48D );
    }
}
