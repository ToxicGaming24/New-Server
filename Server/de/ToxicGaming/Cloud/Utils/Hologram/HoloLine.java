package de.ToxicGaming.Cloud.Utils.Hologram;

import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public interface HoloLine {
	  Packet[] getSpawnPackets( Location location );
	  Packet[] getDespawnPackets( );
}
