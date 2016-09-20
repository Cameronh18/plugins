package me.cameronh.distribution;

import me.cameronh.distribution.config.Message;
import me.cameronh.distribution.events.SetChest;
import me.cameronh.distribution.events.SpawnChest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("distribution")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can use this command");
				return true;
			}
			else {
				final Player player = (Player) sender;
				if(args.length != 0) {
					if(args[0].equalsIgnoreCase("chestCreate")) {
						if(player.hasPermission("distribution.chestcreate")) {
							SetChest.setChest(player);
							return true;
						}
						else {
							Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.NoPerm"));
							return true;
						}
					}
					if(args[0].equalsIgnoreCase("chestSpawn")) {
						if(player.hasPermission("distribution.chestspawn"))
							SpawnChest.chestSpawn(player);
						return true;
					}
					else {
						Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.NoPerm"));
						return true;
					}
				}
			}
		}
		return false;
	}
}
