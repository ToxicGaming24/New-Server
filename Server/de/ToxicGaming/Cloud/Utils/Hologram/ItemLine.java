package de.ToxicGaming.Cloud.Utils.Hologram;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.EntityItem;
import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public class ItemLine implements HoloLine {
    private int armorStandID;
    private int itemID;
    private ItemStack itemStack;
    public ItemLine( int armorStandID, int itemID, ItemStack itemStack ) {
        this.armorStandID = armorStandID;
        this.itemID = itemID;
        this.itemStack = itemStack;
    }
    @Override
    public Packet[] getSpawnPackets ( Location location ) {
        EntityItem entityItem = PacketHelper.createItem( location, itemStack, itemID );
        return new Packet[] {
                PacketHelper.spawnArmorStand( location, "clearline", armorStandID ),
                PacketHelper.itemSpawn( entityItem ),
                PacketHelper.itemMeta( itemID, entityItem.getDataWatcher() ),
                PacketHelper.attachItemToArmorStand( itemID, armorStandID )
        };
    }

    @Override
    public Packet[] getDespawnPackets ( ) {
        return new Packet[] {
                PacketHelper.destroyEntity( itemID ),
                PacketHelper.destroyEntity( armorStandID )
        };
    }
}
