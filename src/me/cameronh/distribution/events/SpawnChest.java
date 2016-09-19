package me.cameronh.distribution.events;

import java.util.ArrayList;

import me.cameronh.distribution.Distribution;
import me.cameronh.distribution.Timer;
import me.cameronh.distribution.config.Default;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class SpawnChest {
	private static int time = Default.defaultConfig.getInt("Distribution.DelayStart") * 20;
	private static int l = Default.defaultConfig.getInt("Distribution.DelaySpawn") * 20;
	private static int i = (Default.defaultConfig.getInt("Distribution.TimeSpawn") * 20) / l;
	public static ArrayList<Location> listLocation = new ArrayList<Location>();
	private static BukkitScheduler scheduler = Bukkit.getScheduler();
	public static void chestSpawn(final Player player) {
		Runnable runnable = new Runnable() {
			public void run() {
				Timer.getTimer(time);
				if(time > 0) {
				time -= 20;
				scheduler.cancelTask(0);
				}
			}
		};
		scheduler.scheduleSyncRepeatingTask(Distribution.getInstance(), runnable, 0L, 20L);
		Runnable runnable2 = new Runnable() {
			final Selection selection = Distribution.getWorldEdit().getSelection(player);
			public void run() {
				if(time == 0) {
					if(i > 0) {
						int y = selection.getMinimumPoint().getBlockY() + (int)(Math.random() * (selection.getMaximumPoint().getBlockY() - selection.getMinimumPoint().getBlockY()));
						recSpawn(player, y);
						i--;
					}
					else {
						i = (Default.defaultConfig.getInt("Distribution.TimeSpawn") * 20) / l;
						time = Default.defaultConfig.getInt("Distribution.DelayStart") * 20;
						scheduler.cancelAllTasks();
					}
				}
			}
		};
		scheduler.scheduleSyncRepeatingTask(Distribution.getInstance(), runnable2, 0L, l);
	}
	public static void recSpawn(Player player, int y) {
		final Selection selection = Distribution.getWorldEdit().getSelection(player);
		int x = selection.getMinimumPoint().getBlockX() + (int)(Math.random() * (selection.getMaximumPoint().getBlockX() - selection.getMinimumPoint().getBlockX()));
		int z = selection.getMinimumPoint().getBlockZ() + (int)(Math.random() * (selection.getMaximumPoint().getBlockZ() - selection.getMinimumPoint().getBlockZ()));
		Location location = new Location(selection.getWorld(), x, y, z);
		Location location2 = new Location(selection.getWorld(), x, y - 1, z);
		if(location.getBlock().getType() == Material.AIR && location2.getBlock().getType() != Material.AIR){
			location.getBlock().setType(Material.CHEST);
			location.getWorld().playEffect(location, Effect.STEP_SOUND, 1);
			listLocation.add(location);
			return;
		}
		else if(location.getBlock().getType() != Material.AIR)
		{
			recSpawn(player, y + 1);
		}
		else if(location2.getBlock().getType() == Material.AIR)
		{
			recSpawn(player, y - 1);
		}
	}
}