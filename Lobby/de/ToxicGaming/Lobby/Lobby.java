package de.ToxicGaming.Lobby;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import de.ToxicGaming.Lobby.Commands.Build;
import lombok.Getter;

public class Lobby extends JavaPlugin{
	@Getter
	private static String prefix = "§6§lLobby §r§8|§2 ";
	@Getter
	private static Lobby instance;
	@Override
	public void onEnable() {
		Bukkit.getScheduler().runTaskTimer(this, ()-> {
			if(LobbyClient.getSpawnLocation().getWorld().getTime() >= 7000) {
				LobbyClient.getSpawnLocation().getWorld().setTime(6000);
			}
			LobbyClient.getSpawnLocation().getWorld().setThundering(false);
			LobbyClient.getSpawnLocation().getWorld().setStorm(false);
		}, 20L, 20L);
		instance = this;
		PluginManager pm = Bukkit.getPluginManager();
		try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.ToxicGaming.Lobby.Listener" )) {
                @SuppressWarnings("rawtypes")
				Class clazz = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pm.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        } catch ( IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
            e.printStackTrace();
        }
		getCommand("build").setExecutor(new Build());
	}
	@Override
	public void onDisable() {
	}
}
