package me.cameronh.distribution.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Default {
	public static FileConfiguration defaultConfig;
	public static void createConfig() {
		if(defaultConfig.getConfigurationSection("Distribution") == null) {
			defaultConfig.set("Distribution.DelayStart", 60);
			defaultConfig.set("Distribution.DelaySpawn", 2);
			defaultConfig.set("Distribution.TimeSpawn", 120);
		}
	}
}
