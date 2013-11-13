package me.schaubie12.plugin;

import me.schaubie12.plugin.ClassManager.Player_Class;

import org.bukkit.Location;

public class PlayerClass 
{
	private Location spawn;
	private Player_Class c;
	
	public PlayerClass(Player_Class c)
	{
		this.c = c;
	}
	
	public void initializeSpawn(Location l)
	{
		spawn = l;
	}
	
	public Location getSpawn()
	{
		return spawn;
	}
}
