package de.ToxicGaming.Bungeecord;

import de.ToxicGaming.Bungeecord.Listener.PlayerJoin;
import de.ToxicGaming.Bungeecord.Listener.ServerKick;
import de.ToxicGaming.Bungeecord.Utilities.TimeOutManager;
import de.ToxicGaming.Bungeecord.commands.Alert;
import de.ToxicGaming.Bungeecord.commands.Goto;
import de.ToxicGaming.Bungeecord.commands.Kick;
import de.ToxicGaming.Bungeecord.commands.Msg;
import de.ToxicGaming.Bungeecord.commands.Ping;
import de.ToxicGaming.Bungeecord.commands.TeamChat;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeProxy extends Plugin { 
	@Getter
	private static String prefix = "§6§lMakrali §r§8|§6 ";
	@Getter
	private static BungeeProxy instance;
	@Getter
	private static boolean isWartung = false;
	@Getter
	private static TimeOutManager timeOutmanager;
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		ProxyServer.getInstance().getConsole().sendMessage("Servercloud startet!");
		instance = this;
		/*
		 * Register Listener ...
		 */
		PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
		pluginManager.registerListener(getInstance(), new PlayerJoin());
		pluginManager.registerListener(getInstance(), new ServerKick());
		/*
		 * Register Commands ...
		 */
		pluginManager.registerCommand(getInstance(), new Goto());
		pluginManager.registerCommand(getInstance(), new Ping());
		pluginManager.registerCommand(getInstance(), new TeamChat());
		pluginManager.registerCommand(getInstance(), new Alert());
		pluginManager.registerCommand(getInstance(), new Msg());
		pluginManager.registerCommand(getInstance(), new Kick());
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			ProxyServer.getInstance().getConsole().sendMessage("Stopping Servercloud ...");
		}));
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onLoad() {
		ProxyServer.getInstance().getConsole().sendMessage("Starting up ServerCloud ...");
	}
	@SuppressWarnings("deprecation")
	public static void sendToProxy(String message) {
		getProxyServer().getConsole().sendMessage(message);
	}
	public static void sendComponentToProxy(BaseComponent bc) {
		getProxyServer().getConsole().sendMessage(bc);
	}
	public static ProxyServer getProxyServer() {
		return ProxyServer.getInstance();
	}
}
