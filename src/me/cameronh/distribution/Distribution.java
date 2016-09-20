package me.cameronh.distribution;

import me.cameronh.distribution.config.Default;
import me.cameronh.distribution.config.Message;
import me.cameronh.distribution.events.ChestBreak;
import me.cameronh.distribution.events.SetChest;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Distribution extends JavaPlugin {
	
	private static Distribution instance;
	public static Distribution getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new ChestBreak(), this);
		Bukkit.getPluginManager().registerEvents(new SetChest(), this);
		getCommand("distribution").setExecutor(new Commands());
		Default.defaultConfig = getConfig();
		Default.createConfig();
		SetChest.createConfig();
		Message.createConfig();
		saveConfig();
		reloadConfig();
		SetChest.saveConfig();
		Message.saveConfig();
	}
    public static WorldEditPlugin getWorldEdit() {
        Plugin worldedit = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (worldedit instanceof WorldEditPlugin) return (WorldEditPlugin) worldedit;
        else return null;
    }
}
