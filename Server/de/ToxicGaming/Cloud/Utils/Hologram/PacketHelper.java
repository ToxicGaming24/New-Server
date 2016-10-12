package de.ToxicGaming.Cloud.Utils.Hologram;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityItem;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

@SuppressWarnings("rawtypes")
public class PacketHelper {
	public static Packet spawnArmorStand( Location location, String name, int entityID ) {
        EntityArmorStand entityArmorStand = new EntityArmorStand( ( ( CraftWorld ) location.getWorld( ) ).getHandle() );
        entityArmorStand.setLocation( location.getX( ), location.getY( ), location.getZ( ), 0, 0 );
        if( name.equals( "clearline" ) ) {
            entityArmorStand.setCustomNameVisible( false );
        } else {
            entityArmorStand.setCustomNameVisible( true );
            entityArmorStand.setCustomName( name );
        }
        entityArmorStand.setInvisible( true );
        entityArmorStand.setGravity( true );
        try {
            Field field = Entity.class.getDeclaredField( "id" );
            field.setAccessible( true );
            field.setInt( entityArmorStand, entityID );
        } catch ( NoSuchFieldException e ) {
            e.printStackTrace( );
        } catch ( IllegalAccessException e ) {
            e.printStackTrace( );
        }
        return new PacketPlayOutSpawnEntityLiving( entityArmorStand );
    }
    public static EntityItem createItem( Location location, org.bukkit.inventory.ItemStack itemStack, int entityID ) {
        EntityItem entityItem = new EntityItem( ( ( CraftWorld ) location.getWorld( ) ).getHandle() );
        entityItem.setItemStack( CraftItemStack.asNMSCopy( itemStack ) );
        entityItem.setLocation( location.getX( ), location.getY( ), location.getZ( ), 0F, 0F );
        try {
            Field field = Entity.class.getDeclaredField( "id" );
            field.setAccessible( true );
            field.setInt( entityItem, entityID );
        } catch ( NoSuchFieldException e ) {
            e.printStackTrace( );
        } catch ( IllegalAccessException e ) {
            e.printStackTrace( );
        }
        return entityItem;
    }
    public static Packet itemSpawn( EntityItem entityItem ) {
        return new PacketPlayOutSpawnEntity( entityItem, 2 );
    }
    public static Packet itemMeta( int itemID, DataWatcher dataWatcher ) {
       return new PacketPlayOutEntityMetadata( itemID, dataWatcher, true );
    }
    public static Packet attachItemToArmorStand( int itemID, int armorStandID ) {
        PacketPlayOutAttachEntity packetPlayOutAttachEntity = new PacketPlayOutAttachEntity(  );
        try {
            Field unknown = PacketPlayOutAttachEntity.class.getDeclaredField( "a" );
            unknown.setAccessible( true );
            unknown.set( packetPlayOutAttachEntity, 0 );
            Field entityID = PacketPlayOutAttachEntity.class.getDeclaredField( "b" );
            entityID.setAccessible( true );
            entityID.set( packetPlayOutAttachEntity, itemID );
            Field vehicleID = PacketPlayOutAttachEntity.class.getDeclaredField( "c" );
            vehicleID.setAccessible( true );
            vehicleID.set( packetPlayOutAttachEntity, armorStandID );
        } catch ( NoSuchFieldException e ) {
            e.printStackTrace( );
        } catch ( IllegalAccessException e ) {
            e.printStackTrace( );
        }
        return packetPlayOutAttachEntity;
    }
    public static Packet destroyEntity( int entityID ) {
        return new PacketPlayOutEntityDestroy( new int[] { entityID } );
    }
}
