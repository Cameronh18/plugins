package me.cameronh.distribution;

import me.cameronh.distribution.config.Message;

import org.bukkit.Bukkit;

public class Timer{
	public static void getTimer(int time) {
		if(time % 1200 == 0 && time > 1200) {
			int i = time / 1200;
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.DelayStartMinute").replace("{time}", "" + i));
		}
		else if(time % 600 == 0 && time == 1200) {
			int i = time / 20;
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.DelayStartSecond").replace("{time}", "" + i));
		}
		else if(time % 200 == 0 && time <= 600 && time > 200) {
			int i = time / 20;
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.DelayStartSecond").replace("{time}", "" + i));
		}
		else if(time % 100 == 0 && time <= 200 && time > 100) {
			int i = time / 20;
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.DelayStartSecond").replace("{time}", "" + i));
		}
		else if(time % 20 == 0 && time <= 100 && time > 0) {
			int i = time / 20;
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.DelayStartSecond").replace("{time}", "" + i));
		}
		if(time == 20) {
			Bukkit.broadcastMessage(Message.getConfig().getString("Message.Prefix") + Message.getConfig().getString("Message.Start"));
		}
	}
}
