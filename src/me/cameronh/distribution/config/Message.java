package me.cameronh.distribution.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Message {
	private static File messageFile = new File("plugins/DistributionChest", "Message.yml");
	private static FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);
	public static FileConfiguration getConfig() {
		return messageConfig;
	}
	
	public static void createConfig() {
		if(messageConfig.getConfigurationSection("Message") == null) {
			messageConfig.set("Message.Prefix", ChatColor.GREEN + "[Раздача] ");
			messageConfig.set("Message.DelayStartMinute", ChatColor.GREEN + "До начала раздачи осталось: {time} минут");
			messageConfig.set("Message.DelayStartSecond", ChatColor.GREEN + "До начала раздачи осталось: {time} секунд");
			messageConfig.set("Message.Start", ChatColor.GREEN + "Раздача началась!");
			messageConfig.set("Message.End", ChatColor.GREEN + "Раздача закончилась!");
			messageConfig.set("Message.Drop", ChatColor.GOLD + "Вам выпал предмет: {item} в количестве {amount} штук");
			messageConfig.set("Message.NoSelection", ChatColor.RED + "Выделите территорию!");			
			messageConfig.set("Message.NoPerm", ChatColor.RED + "У вас недостаточно прав");
		}
	}
	
	public static void saveConfig() {
		try {
			messageConfig.save(messageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
