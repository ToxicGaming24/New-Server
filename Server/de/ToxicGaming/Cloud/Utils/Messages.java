package de.ToxicGaming.Cloud.Utils;

import org.bukkit.entity.Player;

public class Messages {
	public enum MessageTypes {
		NOPERMISSIONS, 
	}
	@SuppressWarnings("null")
	public static void sendErrorMessage(Player player, MessageTypes errorType) {
		CColor c = null;
		if(errorType == MessageTypes.NOPERMISSIONS) {
			player.sendMessage(c.red+"Du hast "+c.line+"keine "+ c.reset+c.red+"Berechtigung um diesen Befehl ausführen zu können!");
		}
	}
}