package de.ToxicGaming.Cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import de.ToxicGaming.Cloud.Config.Config;
import de.ToxicGaming.Cloud.Utils.MongoDB;
import de.ToxicGaming.Cloud.Utils.Tablist;
import de.ToxicGaming.Cloud.Utils.TimeManager;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.MinecraftServer;

public class Cloud extends JavaPlugin {
	@Getter
	private static Cloud instance;
	@Getter
	public static String prefix = "§6§lMakrali.net §r§8| §6";
	@Getter
	private static Config configFile;
	@Getter
	private static Tablist tablist;
	private static final Logger logger = Logger.getLogger("Cloud");
	@Getter
	private static TimeManager timeManager;
	@Getter
	private static long startTime = System.currentTimeMillis();
	@Getter
	private static MongoDB dbPool;
	@Getter
	private static List<Player> banned = new ArrayList<>();
	private static MinecraftServer server;
	@Override
	public void onEnable() {
		dbPool = new MongoDB();
		PluginManager pm = Bukkit.getPluginManager();
		try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.ToxicGaming.Cloud.Listener" )) {
                @SuppressWarnings("rawtypes")
				Class clazz = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pm.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        } catch ( IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e ) {
            e.printStackTrace();
        }
		logger.setParent(this.getLogger());
		logger.log(Level.INFO, "Server started up with "+ServerStats.getMaxPlayers()+" maximal Players!");
		instance = this;
		server.getPropertyManager().setProperty("debug", true);
	}
	@Override
	public void onDisable() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Cloud stopping ...");
		}));
	}
	@Override
	public void onLoad() {
		System.out.println("Preparing Cloud by ToxicGaming to Start ...");
		System.setProperty("file.encoding", "UTF-8");
	}
}