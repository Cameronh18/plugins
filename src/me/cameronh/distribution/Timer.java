package me.cameronh.distribution;

import org.bukkit.Bukkit;

public class Timer{
	public static void getTimer(int time) {
		if(time % 1200 == 0 && time > 1200) {
			Bukkit.broadcastMessage("�� ������ ������� ��������: " + time / 1200 + " �����");
		}
		else if(time % 600 == 0 && time == 1200) {
			Bukkit.broadcastMessage("�� ������ ������� ��������: " + time / 20 + " ������");
		}
		else if(time % 200 == 0 && time <= 600 && time > 200) {
			Bukkit.broadcastMessage("�� ������ ������� ��������: " + time / 20 + " ������");
		}
		else if(time % 20 == 0 && time <= 200 && time > 0) {
			Bukkit.broadcastMessage("�� ������ ������� ��������: " + time / 20 + " ������");
		}
		if(time == 20) {
			Bukkit.broadcastMessage("������� ��������!");
		}
	}
}