package me.cameronh.distribution.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ChestBreak implements Listener {
	private static FileConfiguration chestConfig = SetChest.chestConfig;
	@EventHandler
	public void clickBlock (PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ArrayList<?> list = SpawnChest.listLocation;
			if(event.getClickedBlock().getType() == Material.CHEST) {
				List<?> items = chestConfig.getList("Chest");
				int random = new Random().nextInt(items.size());
				for(int j = 0; j < list.size(); j++) {
					Location location = (Location) list.get(j);
					if(event.getClickedBlock().getLocation().equals(location)) {
						for(int i = 0; i < items.size(); i++) {
							if(random == i) {
								event.setCancelled(true);
								event.getClickedBlock().setType(Material.AIR);
								event.getClickedBlock().getLocation().getWorld().playEffect(location, Effect.SMOKE, 10, 10);
								ItemStack item = (ItemStack) items.get(i);
								event.getPlayer().getInventory().addItem(item);
								event.getPlayer().sendMessage("Вам выпал предмет: " + item.getType() + " в количестве " + item.getAmount() + "штук");
								list.remove(j);
							}
						}
					}
				}
			}
		}
	}
}