package de.ToxicGaming.Cloud.Automessenger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

import de.ToxicGaming.Cloud.Cloud;

public class AutoMessager {
	private static int currentMessage = 0;
	private static int normalMessage = 0;
	@SuppressWarnings("unused")
	private static int normalMessageDefault = 300;
	private static List<String[]> autoMessages = new ArrayList<>();
	
	public static void loadMessages() {
		try{
			autoMessages.clear();
			currentMessage = 0;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Cloud.getInstance().getDataFolder().getAbsolutePath(), "automessage.properties")), "UTF-8"));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				if(line.startsWith("count:")) {
					normalMessageDefault = Integer.valueOf(line.split("count:")[1]);
					normalMessage = 0;
					continue;
				}
				autoMessages.add(line.split(";"));		
			}
			bufferedReader.close();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void setupAutoMessager() {
		new Thread(() -> {
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					if(normalMessage == 0) {
						normalMessage = 300;
						if(currentMessage >= autoMessages.size()) {
							currentMessage = 0;
						}
						List<String[]> mData = new ArrayList<>();
						mData.add(autoMessages.get(currentMessage));
						
					}
				}
			}, 1000L, 1000L);
		}).start();
	}
}
