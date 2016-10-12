package de.ToxicGaming.Cloud.Utils;

import java.util.HashMap;
import java.util.Map;

public enum Gamemodes {
	CREATIVE(1),
	SURVIVAL(0),
	ADVENTURE(2),
	SPECTATOR(3);
	
	private final int value;
	private final static Map<Integer,Gamemodes> by_id = new HashMap<>();
	
	private Gamemodes(final int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
//	private static Gamemodes getByValue(final int value) {
//		return by_id.get(value);
//	}
	static {
		for(Gamemodes mode : values()) {
			by_id.put(mode.getValue(), mode);
		}
	}
}
