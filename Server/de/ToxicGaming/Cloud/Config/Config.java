package de.ToxicGaming.Cloud.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import de.ToxicGaming.Cloud.Cloud;
import lombok.Getter;
import lombok.Setter;

public class Config {
	@Getter
	@Setter
	private static String database;
	@Getter
	@Setter
	private static String user;
	@Getter
	@Setter
	private static String host;
	@Getter
	@Setter
	private static int port;
	@Getter
	@Setter
	private static String password;
//	public static void readConfig() {
//	}
//	public static void setStandartConfig() {
//	}
	public void readMySQL() {
		File file = new File(Cloud.getInstance().getDataFolder(), "MySQL.yml");
		if(!file.exists()) {
			setStandartMySQL();
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		database = config.getString("Database");
		user = config.getString("User");
		port = config.getInt("Port");
		host = config.getString("Host");
		password = config.getString("Password");
	}
	public void setStandartMySQL() {
		File mySqlFile = new File("plugins/Cloud", "MySQL.yml");
		if(!mySqlFile.exists()) {
			try {
				mySqlFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mySqlFile);
		yamlConfiguration.options().copyDefaults(true);
		yamlConfiguration.addDefault("Database", "database");
		yamlConfiguration.addDefault("User", "root");
		yamlConfiguration.addDefault("Host", "localhost");
		yamlConfiguration.addDefault("Port", 3306);
		yamlConfiguration.addDefault("Password", "password");
	}
}
