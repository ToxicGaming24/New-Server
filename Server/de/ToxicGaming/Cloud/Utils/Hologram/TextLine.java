package de.ToxicGaming.Cloud.Utils.Hologram;

import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public class TextLine implements HoloLine {
    private String text;
    private int entityID;
    public TextLine( String text, int entityID ) {
        this.text = text;
        this.entityID = entityID;
    }

    @Override
    public Packet[] getSpawnPackets ( Location location ) {
        Packet spawnPacket = PacketHelper.spawnArmorStand( location, text, entityID );
        return new Packet[] {
                spawnPacket
        };
    }

    @Override
    public Packet[] getDespawnPackets ( ) {
        return new Packet[] {
                PacketHelper.destroyEntity( entityID )
        };
    }
}
