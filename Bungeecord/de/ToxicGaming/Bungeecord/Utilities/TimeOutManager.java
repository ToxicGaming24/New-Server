package de.ToxicGaming.Bungeecord.Utilities;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public class TimeOutManager {
	@Getter
	private ConcurrentHashMap<String, Long> playerTimeout = new ConcurrentHashMap<>();
	public boolean checkTimeout(String name) {
		if(playerTimeout.containsKey(name)) {
			if(playerTimeout.get(name) > System.currentTimeMillis()) {
				return false;
			}
		}
		playerTimeout.put(name, System.currentTimeMillis()+500);
		return true;
	}
	public void logOut(String name) {
		playerTimeout.remove(name);
	}
}
