package me.cameronh.distribution;

import org.bukkit.Bukkit;

public class Timer{
	public static void getTimer(int time) {
		if(time % 1200 == 0 && time > 1200) {
			Bukkit.broadcastMessage("До начала раздачи осталось: " + time / 1200 + " минут");
		}
		else if(time % 600 == 0 && time == 1200) {
			Bukkit.broadcastMessage("До начала раздачи осталось: " + time / 20 + " секунд");
		}
		else if(time % 200 == 0 && time <= 600 && time > 200) {
			Bukkit.broadcastMessage("До начала раздачи осталось: " + time / 20 + " секунд");
		}
		else if(time % 20 == 0 && time <= 200 && time > 0) {
			Bukkit.broadcastMessage("До начала раздачи осталось: " + time / 20 + " секунд");
		}
		if(time == 20) {
			Bukkit.broadcastMessage("Раздача началась!");
		}
	}
}