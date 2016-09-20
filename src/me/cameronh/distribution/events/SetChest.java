package me.cameronh.distribution.events;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetChest implements Listener {

	private static File chestFile = new File("plugins/DistributionChest", "Chest.yml");
	private static FileConfiguration chestConfig = YamlConfiguration.loadConfiguration(chestFile);
	public static FileConfiguration getConfig() {
		return chestConfig;
	}

	public static void createConfig() {
		if(chestConfig.getString("Chest") == null) {
			chestConfig.set("Chest", null);
		}
	}

	public static void setChest(Player player) {
		ItemStack saveChest = new ItemStack(Material.CHEST, 1);
		ItemMeta saveMeta = saveChest.getItemMeta();
		saveMeta.setDisplayName(ChatColor.GOLD + "Сохранить сундук");
		saveChest.setItemMeta(saveMeta);
		Inventory inventory = Bukkit.createInventory(null, 54, "Сундук для раздачи");
		inventory.setItem(49, saveChest);
		if(chestConfig.get("Chest") != null){
			ItemStack[] contents = inventory.getContents();
			List<?> items = chestConfig.getList("Chest");
			for(int i = 0; i < items.size(); i++)
			{
				contents[i] = (ItemStack) items.get(i); 
			}
			inventory.setContents(contents);
		}
		inventory.setItem(49, saveChest);
		player.openInventory(inventory);
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory().getName().equalsIgnoreCase("Сундук для раздачи")) {
			if(event.getRawSlot() == 49) {
				event.setCancelled(true);
				chestConfig.set("Chest", null);
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				ItemStack[] content = event.getInventory().getContents();
				for(int i = 0; i < content.length; i++){
					ItemStack item = content[i];
					if(!(item == null) && item.getType() != Material.CHEST)
					{
						items.add(item);
					}
				}
				chestConfig.set("Chest", items);
				try {
					chestConfig.save(chestFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				player.closeInventory();
			}
		}
	}

	public static void saveConfig() {
		try {
			chestConfig.save(chestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
